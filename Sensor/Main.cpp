#include <HardwareSerial.h>

#include "dht_nonblocking.h"
#include "Sender.h"
#include "Helper.h"

#define DHT_SENSOR_TYPE DHT_TYPE_11

static const int DHT_SENSOR_PIN = 2;
DHT_nonblocking dhtSensor( DHT_SENSOR_PIN, DHT_SENSOR_TYPE );

Sender* sender;
unsigned long max;


/* Does work of sending a message */
void messageSendingProcess(float* temperature, float* humidity);


void setup() {
	sender = new Sender();
	max = 3000ul;
}


void loop() {
	float temperature;
	float humidity;

	messageSendingProcess(&temperature, &humidity);
}


void messageSendingProcess(float* temperature, float* humidity) {
	// Check if new message should be send
	bool isMeasurementAvailable = Helper::measureEnvironment(
			temperature,
			humidity,
			max,
			&dhtSensor
	);

	if (isMeasurementAvailable == true) {
		// sending a new message
		sender->sendTemperature(temperature);
		sender->sendHumidity(humidity);
	}
}
