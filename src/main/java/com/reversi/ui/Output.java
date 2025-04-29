package com.reversi.ui;

import com.reversi.core.logic.exceptions.GameException;
import com.reversi.model.output.OutputInformation;

public interface Output {
    void print(OutputInformation output);
    void printError(GameException e, OutputInformation output);
} 