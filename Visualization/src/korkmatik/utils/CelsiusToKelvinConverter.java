package korkmatik.utils;

public class CelsiusToKelvinConverter implements Converter {

	@Override
	public float convert(float value) {
		return (value + 273.15f);
	}

}
