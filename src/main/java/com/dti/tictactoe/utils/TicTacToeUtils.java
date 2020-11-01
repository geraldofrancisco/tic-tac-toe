package com.dti.tictactoe.utils;

import com.dti.tictactoe.enums.Player;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TicTacToeUtils {
    public Player chooseRadomPlayer() {
        List<Player> values = Arrays.asList(Player.values());
        Collections.shuffle(values);
        return values.get(0);
    }

    public String encodeMD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            return DatatypeConverter.printHexBinary(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String nowMd5() {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        sb.append(now.getDayOfMonth()).append(now.getMonthValue()).append(now.getYear());
        sb.append(now.getHour()).append(now.getMinute()).append(now.getSecond()).append(now.getNano());
        return encodeMD5(sb.toString());
    }
}
