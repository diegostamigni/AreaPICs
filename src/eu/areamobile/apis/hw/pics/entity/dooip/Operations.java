package eu.areamobile.apis.hw.pics.entity.dooip;

import eu.areamobile.apis.hw.pics.entity.HWOperations;
import eu.areamobile.apis.hw.pics.entity.dooip.json.DooIPJSonFactory;

/**
 * Created by AreaMobile
 * Date: 30/04/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

interface DooIPHWOperations extends HWOperations {
    /**
     * Turn pin to off/on. Acknowledge is false.
     * @param pin the pin you would like to control
     * @param value 0, 1 to set the specific pin to on/off
     * @see HWOperations
     */
    DooIPJSonFactory io_put(int pin, int value);

    /**
     * Turn pin to off/on
     * @param pin the pin you would like to control
     * @param value 0, 1 to set the specific pin to on/off
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory io_put(int pin, int value, boolean acknowledge);

    /**
     * Instantiate the pin with the specified value. Acknowledge is false.
     * @param pin the pin you would like to control
     * @param value for the pin
     * @see HWOperations
     */
    DooIPJSonFactory io_init(int pin, int value);

    /**
     * Instantiate the pin with the specified value
     * @param pin the pin you would like to control
     * @param value for the pin
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory io_init(int pin, int value, boolean acknowledge);

    /**
     * Read the state of the specified pin. Acknowledge is false.
     * @param pin
     * @see HWOperations
     * @return 0, 1 for off/on
     */
    DooIPJSonFactory io_get(int pin);

    /**
     * Read the state of the specified pin.
     * @param pin
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     * @return 0, 1 for off/on
     */
    DooIPJSonFactory io_get(int pin, boolean acknowledge);

    /**
     * Returns if a button was pressed or released. Acknowledge is false.
     * @param pin the pin you would like to control
     * @see HWOperations
     */
    DooIPJSonFactory io_button_state(int pin);

    /**
     * Returns if a button was pressed or released.
     * @param pin the pin you would like to control
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory io_button_state(int pin, boolean acknowledge);

    /**
     * Initialize the ADC<br></br>Acknowledge is false.
     * <b>Note:</b> the AreaFly initialize the ADCs itself
     * @see HWOperations
     */
    DooIPJSonFactory adc_init();

    /**
     * Initialize the ADC<br></br>
     * <b>Note:</b> the AreaFly initialize the ADCs itself
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory adc_init(boolean acknowledge);

    /**
     * Read the value of one of the analogical channels. Acknowledge is false.
     * @param channel is the channel
     * @return - (int) value of the channel between [0-1023]
     * @see HWOperations
     */
    DooIPJSonFactory adc_val(int channel);

    /**
     * Read the value of one of the analogical channels.
     * @param channel is the channel
     * @param acknowledge if you want the acknowledge
     * @return - (int) value of the channel between [0-1023]
     * @see HWOperations
     */
    DooIPJSonFactory adc_val(int channel, boolean acknowledge);

    /**
     * Initialize the port indicated with the value baud. Acknowledge is false.
     * @param port you would like change
     * @param baud is the value for the port
     * @see HWOperations
     */
    DooIPJSonFactory uart_init(int port, long baud);

    /**
     * Initialize the port indicated with the value baud.
     * @param port you would like change
     * @param baud is the value for the port
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory uart_init(int port, long baud, boolean acknowledge);

    /**
     * Activate the serial port. Acknowledge is false.
     * @param port
     * @see HWOperations
     */
    DooIPJSonFactory uart_on(int port);

    /**
     * Activate the serial port.
     * @param port
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory uart_on(int port, boolean acknowledge);

    /**
     * Deactivate the serial port. Acknowledge is false.
     * @param port
     * @see HWOperations
     */
    DooIPJSonFactory uart_off(int port);

    /**
     * Deactivate the serial port
     * @param port
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory uart_off(int port, boolean acknowledge);

    /**
     * Clean the buffer of the port. Acknowledge is false.
     * @param port
     * @see HWOperations
     */
    DooIPJSonFactory uart_flush(int port);

    /**
     * Clean the buffer of the port
     * @param port
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory uart_flush(int port, boolean acknowledge);

    /**
     * Number of characters arrived at the port. Acknowledge is false.
     * @param port
     * @return the number of the characters in the buffer
     * @see HWOperations
     */
    DooIPJSonFactory uart_buffer_size(int port);

    /**
     * Number of characters arrived at the port.
     * @param port
     * @param acknowledge if you want the acknowledge
     * @return the number of the characters in the buffer
     * @see HWOperations
     */
    DooIPJSonFactory uart_buffer_size(int port, boolean acknowledge);

    /**
     * Read n characters from the buffer of the port. Acknowledge is false.
     * @param port of the buffer
     * @param n is the number of characters you'd like to read from the buffer
     * @return the string of the characters read
     * @see HWOperations
     */
    DooIPJSonFactory uart_read(int port, int n);

    /**
     * Read n characters from the buffer of the port
     * @param port of the buffer
     * @param n is the number of characters you'd like to read from the buffer
     * @param acknowledge if you want the acknowledge
     * @return the string of the characters read
     * @see HWOperations
     */
    DooIPJSonFactory uart_read(int port, int n, boolean acknowledge);

    /**
     * Write the string str on the port. Acknowledge is false.
     * @param port
     * @param str
     * @see HWOperations
     */
    DooIPJSonFactory uart_write(int port, String str);

    /**
     * Write the string str on the port
     * @param port
     * @param str
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory uart_write(int port, String str, boolean acknowledge);

    /**
     * Write the character ch on hte port. Acknowledge is false.
     * @param port
     * @param ch
     * @see HWOperations
     */
    DooIPJSonFactory uart_write_ch(int port, char ch);

    /**
     * Write the character ch on hte port
     * @param port
     * @param ch
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory uart_write_ch(int port, char ch, boolean acknowledge);

    /**
     * Initialize the <b>pwm</b> at the <b>freq</b> with the <b>duty</b> cycle. Acknowledge is false.
     * @param n
     * @param freq
     * @param duty
     * @see HWOperations
     */
    DooIPJSonFactory pwm_init(byte n, float freq, float duty);

    /**
     * Initialize the <b>pwm</b> at the <b>freq</b> with the <b>duty</b> cycle.
     * @param n
     * @param freq
     * @param duty
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory pwm_init(byte n, float freq, float duty, boolean acknowledge);

    /**
     * Activate the wave indicated by pwm on the pin. Acknowledge is false.
     * @param pin
     * @param pwm
     * @see HWOperations
     */
    DooIPJSonFactory pwm_on (byte pin, byte pwm);

    /**
     * Activate the wave indicated by pwm on the pin
     * @param pin
     * @param pwm
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory pwm_on (byte pin, byte pwm, boolean acknowledge);

    /**
     * Set the duty cycle of the pwm. Acknowledge is false.
     * @param duty
     * @param pwm
     * @see HWOperations
     */
    DooIPJSonFactory pwm_duty(float duty, byte pwm);

    /**
     * Set the duty cycle of the pwm
     * @param duty
     * @param pwm
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory pwm_duty(float duty, byte pwm, boolean acknowledge);

    /**
     * Turn-off the pwm. Acknowledge is false.
     * @param pwm
     * @see HWOperations
     */
    DooIPJSonFactory pwm_off(byte pwm);

    /**
     * Turn-off the pwm
     * @param pwm
     * @param acknowledge if you want the acknowledge
     * @see HWOperations
     */
    DooIPJSonFactory pwm_off(byte pwm, boolean acknowledge);
}
