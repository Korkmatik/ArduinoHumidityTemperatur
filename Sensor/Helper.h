/*
 * Helper.h
 *
 *  Created on: 17.04.2020
 *      Author: fahri
 */

#ifndef HELPER_H_
#define HELPER_H_

class DHT_nonblocking;

class Helper {
public:
	Helper();
	virtual ~Helper();

	static int getNumberOfDigits(int number);
	static bool measureEnvironment(
			float *temperature,
			float *humidity,
			const unsigned long &max,
			DHT_nonblocking* dhtSensor);
};

#endif /* HELPER_H_ */
