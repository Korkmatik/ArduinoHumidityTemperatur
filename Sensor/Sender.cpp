/*
 * Sender.cpp
 *
 *  Created on: 17.04.2020
 *      Author: fahri
 */

#include "Sender.h"

#include <HardwareSerial.h>

Sender::Sender() {
	Serial.begin(9600);
}

Sender::~Sender() { }

void Sender::sendTemperature(float* temperature) {

	// Communication protocol
	Serial.print("(TEMP#");
	Serial.print(*temperature, 1);
	Serial.print(")");
}

void Sender::sendHumidity(float* humidity) {

	// Communication protocol for humidity
	Serial.print("(HUMI#");
	Serial.print(*humidity, 1);
	Serial.print(")");
}
