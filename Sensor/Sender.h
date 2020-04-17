/*
 * Sender.h
 *
 *  Created on: 17.04.2020
 *      Author: fahri
 */

#ifndef SENDER_H_
#define SENDER_H_

class Sender {
public:
	Sender();
	virtual ~Sender();

	void sendTemperature(float* temperature);
	void sendHumidity(float* humidity);
};

#endif /* SENDER_H_ */
