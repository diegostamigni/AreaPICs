package eu.areamobile.apis.hw.pics.entity;

/**
 * Created by AreaMobile
 * Date: 30/04/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public interface HWOperations {
    // TYPE
    /*
        #define B_YTE 0
        #define INT 1
        #define FLOAT 2
        #define DOUBLE 3
        #define CHAR 4
        #define LONG 5
        #define STRING 6
     */
    public final static short TYPE_BYTE = 0;
    public final static short TYPE_INT = 1;
    public final static short TYPE_FLOAT = 2;
    public final static short TYPE_DOUBLE = 3;
    public final static short TYPE_CHAR = 4;
    public final static short TYPE_LONG = 5;
    public final static short TYPE_STRING = 6;

    // PIN final static NUMBERS
    public final static short NUMBER_PIN_1 = 1;
    public final static short NUMBER_PIN_2 = 2;
    public final static short NUMBER_PIN_3 = 3;
    public final static short NUMBER_PIN_4 = 4;
    public final static short NUMBER_PIN_5 = 5;
    public final static short NUMBER_PIN_6 = 6;
    public final static short NUMBER_PIN_7 = 7;
    public final static short NUMBER_PIN_8 = 8;
    public final static short NUMBER_PIN_9 = 9;
    public final static short NUMBER_PIN_10 = 10;
    public final static short NUMBER_PIN_11 = 11;
    public final static short NUMBER_PIN_12 = 12;
    public final static short NUMBER_PIN_13 = 13;
    public final static short NUMBER_PIN_14 = 14;
    public final static short NUMBER_PIN_15 = 15;
    public final static short NUMBER_PIN_16 = 16;
    public final static short NUMBER_PIN_17 = 17;
    public final static short NUMBER_PIN_18 = 18;
    public final static short NUMBER_PIN_19 = 19;
    public final static short NUMBER_PIN_20 = 20;
    public final static short NUMBER_PIN_21 = 21;
    public final static short NUMBER_PIN_22 = 22;
    public final static short NUMBER_PIN_23 = 23;
    public final static short NUMBER_PIN_24 = 24;
    public final static short NUMBER_PIN_25 = 25;
    public final static short NUMBER_PIN_26 = 26;

    // PORTfinal static  NUMBERS
    public final static short NUMBER_PORT_1 = 1;
    public final static short NUMBER_PORT_2 = 2;
    public final static short NUMBER_PORT_3 = 3;
    public final static short NUMBER_PORT_4 = 4;

    // CHANfinal static NEL NUMBER
    public final static short NUMBER_CHANNEL_1 = 1;
    public final static short NUMBER_CHANNEL_2 = 2;
    public final static short NUMBER_CHANNEL_3 = 3;
    public final static short NUMBER_CHANNEL_4 = 4;
    public final static short NUMBER_CHANNEL_5 = 5;
    public final static short NUMBER_CHANNEL_6 = 6;
    public final static short NUMBER_CHANNEL_7 = 7;
    public final static short NUMBER_CHANNEL_8 = 8;
    public final static short NUMBER_CHANNEL_9 = 9;

    // GROUfinal static Ps
    public final static short GROUP_SINGLE = 0;
    public final static short GROUP_ALL = 255;

    // IOPufinal static t Value
    public final static byte VALUE_IOPUT_ON = 1;
    public final static byte VALUE_IOPUT_OFF = 0;
    public final static byte VALUE_IOPUT_TOGGLE = 2;

    // IOInfinal static it Value
    public final static byte VALUE_IOINIT_OUT = 0;
    public final static byte VALUE_IOINIT_IN = 1;
    public final static byte VALUE_IOINIT_INUP = 2;
    public final static byte VALUE_IOINIT_INDOWN = 3;
    public final static byte VALUE_IOINIT_UART1RX = 5;
    public final static byte VALUE_IOINIT_UART1CTS = 6;
    public final static byte VALUE_IOINIT_UART2RX = 7;
    public final static byte VALUE_IOINIT_UART2CTS = 8;
    public final static byte VALUE_IOINIT_UART3RX = 9;
    public final static byte VALUE_IOINIT_UART3CTS = 10;
    public final static byte VALUE_IOINIT_UART4RX = 11;
    public final static byte VALUE_IOINIT_UART4CTS = 12;
    public final static byte VALUE_IOINIT_EXT_INT2 = 13;
    public final static byte VALUE_IOINIT_EXT_INT3 = 14;
    public final static byte VALUE_IOINIT_EXT_INT4 = 15;
    public final static byte VALUE_IOINIT_SPICLKIN = 16;
    public final static byte VALUE_IOINIT_SPI_IN = 17;
    public final static byte VALUE_IOINIT_SPI_SS = 18;
    public final static byte VALUE_IOINIT_TIM_4_CLK = 19;
    public final static byte VALUE_IOINIT_UART1TX = 31;
    public final static byte VALUE_IOINIT_UART1RTS = 32;
    public final static byte VALUE_IOINIT_UART2TX = 33;
    public final static byte VALUE_IOINIT_UART2RTS = 34;
    public final static byte VALUE_IOINIT_UART3TX = 35;
    public final static byte VALUE_IOINIT_UART3RTS = 36;
    public final static byte VALUE_IOINIT_UART4TX = 37;
    public final static byte VALUE_IOINIT_UART4RTS = 38;
    public final static byte VALUE_IOINIT_SPICLKOUT = 39;
    public final static byte VALUE_IOINIT_SPI_OUT = 40;
    public final static byte VALUE_IOINIT_SPI_SS_OUT = 41;

    // OPCOfinal static DE
    public final static byte OPCODE_SCAN = 0;
    public final static byte OPCODE_IOPUT = 1;
    public final static byte OPCODE_IOINIT = 2;
    public final static byte OPCODE_IOGET = 3;
    public final static byte OPCODE_IOBUTTONSTATE = 4;
    public final static byte OPCODE_ADCINIT = 5;
    public final static byte OPCODE_ADCVAL = 6;
    public final static byte OPCODE_UART_INIT = 7;
    public final static byte OPCODE_UART_ON = 8;
    public final static byte OPCODE_UART_OFF = 9;
    public final static byte OPCODE_UART_FLUSH = 10;
    public final static byte OPCODE_UART_BUFFER_SIZE = 11;
    public final static byte OPCODE_UART_READ = 12;
    public final static byte OPCODE_UART_WRITE = 13;
    public final static byte OPCODE_UART_WRITE_CH = 14;
    public final static byte OPCODE_PWM_INIT = 15;
    public final static byte OPCODE_PWM_ON = 16;
    public final static byte OPCODE_PWM_DUTY = 17;
    public final static byte OPCODE_PWM_OFF = 18;

    // COMMfinal static ON BAUD RATE
    public final static int VALUE_BAUD_LOW = 9600;
    public final static int VALUE_BAUD_MEDIUMLOW = 19200;
    public final static int VALUE_BAUD_MEDIUM = 38400;
    public final static int VALUE_BAUD_MEDIUMHIGH = 57600;
    public final static int VALUE_BAUD_HIGH = 115200;

    public final static int ON = 1;
    public final static int OFF = 0;
}
