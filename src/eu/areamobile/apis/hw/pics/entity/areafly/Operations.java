package eu.areamobile.apis.hw.pics.entity.areafly;

import eu.areamobile.apis.hw.pics.entity.HWOperations;
import eu.areamobile.apis.hw.pics.entity.areafly.json.AreaFlyJSonFactory;

/**
 * Created by AreaMobile
 * Date: 30/04/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

interface AreaFlyHWOperations extends HWOperations {
    /**
     * Turn pin to off/on
     * @param first_type is the type of the first parameter
     * @param pin the pin you would like to control
     * @param second_type is the type of the second parameter
     * @param value 0, 1 to set the specific pin to on/off
     * @see HWOperations
     */
    AreaFlyJSonFactory io_put(int first_type, int pin, int second_type, Object value);

    /**
     * Instantiate the pin with the specified value
     * @param pin the pin you would like to control
     * @param value for the pin
     */
    AreaFlyJSonFactory io_init(int pin, int value);

    /**
     * Read the state of the specified pin
     * @param pin
     * @return 0, 1 for off/on
     */
    AreaFlyJSonFactory io_get(int pin);

    /**
     * Returns if a button was pressed or released
     * @param pin the pin you would like to control
     * @deprecated NEEDS TO BE IMPROVED OR REPLACED WITH EVENTS
     */
    AreaFlyJSonFactory io_button_state(int pin);

    /**
     * Initialize the ADC<br></br>
     * <b>Note:</b> the AreaFly initialize the ADCs itself
     */
    AreaFlyJSonFactory adc_init();

    /**
     * Read the value of one of the analogical channels
     * @param channel is the channel
     * @return - (int) value of the channel between [0-1023]
     */
    AreaFlyJSonFactory adc_val(int channel);

    /**
     * Initialize the port indicated with the value baud
     * @param port you would like change
     * @param baud is the value for the port
     */
    AreaFlyJSonFactory uart_init(int port, long baud);

    /**
     * Activate the serial port
     * @param port
     */
    AreaFlyJSonFactory uart_on(int port);

    /**
     * Deactivate the serial port
     * @param port
     */
    AreaFlyJSonFactory uart_off(int port);

    /**
     * Clean the buffer of the port
     * @param port
     */
    AreaFlyJSonFactory uart_flush(int port);

    /**
     * Number of characters arrived at the port
     * @param port
     * @return the number of the characters in the buffer
     */
    AreaFlyJSonFactory uart_buffer_size(int port);

    /**
     * Read n characters from the buffer of the port
     * @param port of the buffer
     * @param n is the number of characters you'd like to read from the buffer
     * @return the string of the characters read
     */
    AreaFlyJSonFactory uart_read(int port, int n);

    /**
     * Write the string str on the port
     * @param port
     * @param str
     */
    AreaFlyJSonFactory uart_write(int port, String str);

    /**
     * Write the character ch on hte port
     * @param port
     * @param ch
     */
    AreaFlyJSonFactory uart_write_ch(int port, char ch);

    /**
     * Initialize the <b>pwm</b> at the <b>freq</b> with the <b>duty</b> cycle
     * @param n
     * @param freq
     * @param duty
     */
    AreaFlyJSonFactory pwm_init(byte n, float freq, float duty);

    /**
     * Activate the wave indicated by pwm on the pin
     * @param pin
     * @param pwm
     */
    AreaFlyJSonFactory pwm_on (byte pin, byte pwm);

    /**
     * Set the duty cycle of the pwm
     * @param duty
     * @param pwm
     */
    AreaFlyJSonFactory pwm_duty(float duty, byte pwm);

    /**
     * Turn-off the pwm
     * @param pwm
     */
    AreaFlyJSonFactory pwm_off(byte pwm);
}
