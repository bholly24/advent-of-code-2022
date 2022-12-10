package day10

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SignalDecoderTest {
    private val signalDecoder = SignalParser(FileHelper.testFileForDay(10))

    @Test
    fun getSignalStrengthAt20() {
        assertEquals(13140, SignalCalculators.getSignalStrengthAtInterval(signalDecoder.getInstructions()))
    }

    @Test
    fun printSpritePosition() {
        SignalCalculators.printSpritePosition(signalDecoder.getInstructions())
    }
}