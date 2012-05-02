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
    public short TYPE_BYTE = 0;
    public short TYPE_INT = 1;
    public short TYPE_FLOAT = 2;
    public short TYPE_DOUBLE = 3;
    public short TYPE_CHAR = 4;
    public short TYPE_LONG = 5;
    public short TYPE_STRING = 6;

    // PIN NUMBERS
    public short NUMBER_PIN_1 = 1;
    public short NUMBER_PIN_2 = 2;
    public short NUMBER_PIN_3 = 3;
    public short NUMBER_PIN_4 = 4;
    public short NUMBER_PIN_5 = 5;
    public short NUMBER_PIN_6 = 6;
    public short NUMBER_PIN_7 = 7;
    public short NUMBER_PIN_8 = 8;
    public short NUMBER_PIN_9 = 9;
    public short NUMBER_PIN_10 = 10;
    public short NUMBER_PIN_11 = 11;
    public short NUMBER_PIN_12 = 12;
    public short NUMBER_PIN_13 = 13;
    public short NUMBER_PIN_14 = 14;
    public short NUMBER_PIN_15 = 15;
    public short NUMBER_PIN_16 = 16;
    public short NUMBER_PIN_17 = 17;
    public short NUMBER_PIN_18 = 18;
    public short NUMBER_PIN_19 = 19;
    public short NUMBER_PIN_20 = 20;
    public short NUMBER_PIN_21 = 21;
    public short NUMBER_PIN_22 = 22;
    public short NUMBER_PIN_23 = 23;
    public short NUMBER_PIN_24 = 24;
    public short NUMBER_PIN_25 = 25;
    public short NUMBER_PIN_26 = 26;

    // PORT NUMBERS
    public short NUMBER_PORT_1 = 1;
    public short NUMBER_PORT_2 = 2;
    public short NUMBER_PORT_3 = 3;
    public short NUMBER_PORT_4 = 4;

    // CHANNEL NUMBER
    public short NUMBER_CHANNEL_1 = 1;
    public short NUMBER_CHANNEL_2 = 2;
    public short NUMBER_CHANNEL_3 = 3;
    public short NUMBER_CHANNEL_4 = 4;
    public short NUMBER_CHANNEL_5 = 5;
    public short NUMBER_CHANNEL_6 = 6;
    public short NUMBER_CHANNEL_7 = 7;
    public short NUMBER_CHANNEL_8 = 8;
    public short NUMBER_CHANNEL_9 = 9;

    // GROUPs
    public short GROUP_SINGLE = 0;
    public short GROUP_ALL = 255;

    // IOPut Value
    public byte VALUE_IOPUT_ON = 1;
    public byte VALUE_IOPUT_OFF = 0;
    public byte VALUE_IOPUT_TOGGLE = 2;

    // IOInit Value
    public byte VALUE_IOINIT_OUT = 0;
    public byte VALUE_IOINIT_IN = 1;
    public byte VALUE_IOINIT_INUP = 2;
    public byte VALUE_IOINIT_INDOWN = 3;
    public byte VALUE_IOINIT_UART1RX = 5;
    public byte VALUE_IOINIT_UART1CTS = 6;
    public byte VALUE_IOINIT_UART2RX = 7;
    public byte VALUE_IOINIT_UART2CTS = 8;
    public byte VALUE_IOINIT_UART3RX = 9;
    public byte VALUE_IOINIT_UART3CTS = 10;
    public byte VALUE_IOINIT_UART4RX = 11;
    public byte VALUE_IOINIT_UART4CTS = 12;
    public byte VALUE_IOINIT_EXT_INT2 = 13;
    public byte VALUE_IOINIT_EXT_INT3 = 14;
    public byte VALUE_IOINIT_EXT_INT4 = 15;
    public byte VALUE_IOINIT_SPICLKIN = 16;
    public byte VALUE_IOINIT_SPI_IN = 17;
    public byte VALUE_IOINIT_SPI_SS = 18;
    public byte VALUE_IOINIT_TIM_4_CLK = 19;
    public byte VALUE_IOINIT_UART1TX = 31;
    public byte VALUE_IOINIT_UART1RTS = 32;
    public byte VALUE_IOINIT_UART2TX = 33;
    public byte VALUE_IOINIT_UART2RTS = 34;
    public byte VALUE_IOINIT_UART3TX = 35;
    public byte VALUE_IOINIT_UART3RTS = 36;
    public byte VALUE_IOINIT_UART4TX = 37;
    public byte VALUE_IOINIT_UART4RTS = 38;
    public byte VALUE_IOINIT_SPICLKOUT = 39;
    public byte VALUE_IOINIT_SPI_OUT = 40;
    public byte VALUE_IOINIT_SPI_SS_OUT = 41;

    // OPCODE
    public byte OPCODE_SCAN = 0;
    public byte OPCODE_IOPUT = 1;
    public byte OPCODE_IOINIT = 2;
    public byte OPCODE_IOGET = 3;
    public byte OPCODE_IOBUTTONSTATE = 4;
    public byte OPCODE_ADCINIT = 5;
    public byte OPCODE_ADCVAL = 6;
    public byte OPCODE_UARTINIT = 7;
    public byte OPCODE_UART_ON = 8;
    public byte OPCODE_UART_OFF = 9;
    public byte OPCODE_UART_FLUSH = 10;
    public byte OPCODE_UART_BUFFER_SIZE = 11;
    public byte OPCODE_UART_READ = 12;
    public byte OPCODE_UART_WRITE = 13;
    public byte OPCODE_UART_WRITE_CH = 14;
    public byte OPCODE_PWM_INIT = 15;
    public byte OPCODE_PWM_ON = 16;
    public byte OPCODE_PWM_DUTY = 17;
    public byte OPCODE_PWM_OFF = 18;

    // COMMON BAUD RATE
    public int VALUE_BAUD_LOW = 9600;
    public int VALUE_BAUD_MEDIUMLOW = 19200;
    public int VALUE_BAUD_MEDIUM = 38400;
    public int VALUE_BAUD_MEDIUMHIGH = 57600;
    public int VALUE_BAUD_HIGH = 115200;
}
