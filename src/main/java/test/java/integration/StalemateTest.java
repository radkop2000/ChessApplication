package test.java.integration;

import MVC.ManagerUI;
import MVC.GameUI;
import MVC.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

import javax.swing.*;

import java.awt.*;
import java.awt.event.InputEvent;

import static java.lang.Thread.sleep;


class StalemateTest {

    @Test
    void testStalemate() throws InterruptedException {

        Clicker clicker = new Clicker();

        clicker.simulateTileClick(6,4);
        clicker.simulateTileClick(4,4);

        sleep(5000);
    }



}
