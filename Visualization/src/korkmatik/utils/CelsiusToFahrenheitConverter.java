package korkmatik.utils;

public class CelsiusToFahrenheitConverter implements Converter {

	@Override
	public float convert(float value) {
		return ((value * (9/5)) + 32);
	}

}
