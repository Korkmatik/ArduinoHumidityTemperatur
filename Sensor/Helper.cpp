/*
 * Helper.cpp
 *
 *  Created on: 17.04.2020
 *      Author: fahri
 */

#include "Helper.h"

#include <Arduino.h>

#include "dht_nonblocking.h"

Helper::Helper() { }

Helper::~Helper() { }

static int Helper::getNumberOfDigits(int number) {
	int digits = 0;
	if (number < 0) {
		digits = 1;
	}
	while (number != 0) {
		number /= 10;
		digits += 1;
	}

	return digits;
}

static bool Helper::measureEnvironment(
		float *temperature,
		float *humidity,
		const unsigned long &max,
		DHT_nonblocking* dhtSensor)
{
	static unsigned long measurementTimestamp = millis( );

	/* Measure once every four seconds. */
	if(millis( ) - measurementTimestamp > max) {
		if(dhtSensor->measure(temperature, humidity)) {
			measurementTimestamp = millis( );
			return true;
		}
	}

	return false;
}
