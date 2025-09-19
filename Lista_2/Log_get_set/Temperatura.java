package Log_get_set;

class Temperatura {
    private float temp_kelvin;

    public Temperatura(float kelvin) {
        if (kelvin >= 0) {
            this.temp_kelvin = kelvin;
        } else {
            throw new IllegalArgumentException("Temperatura abaixo do zero absoluto!"); }}

    public void setKelvin(float kelvin) {
        if (kelvin >= 0) {
            this.temp_kelvin = kelvin;
        } else {
            System.out.println("Valor abaixo do zero absoluto!");}}

    public void setCelsius(float celsius) {
        float kelvin = celsius + 273.15f;
        setKelvin(kelvin);}

    public void setFahrenheit(float fahrenheit) {
        float kelvin = (fahrenheit - 32) / 1.8f + 273.15f;
        setKelvin(kelvin);
    }

    public float getKelvin() {
        return temp_kelvin;
    }

    public float getCelsius() {
        return temp_kelvin - 273.15f;
    }

    public float getFahrenheit() {
        return (temp_kelvin - 273.15f) * 1.8f + 32; }}