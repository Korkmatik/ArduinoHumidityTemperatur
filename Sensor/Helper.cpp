/*
 * Helper.cpp
 *
 *  Created on: 17.04.2020
 *      Author: fahri
 */

#include "Helper.h"

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
