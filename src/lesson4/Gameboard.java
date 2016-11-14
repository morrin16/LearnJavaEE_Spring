package lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Yury on 27.10.2016.
 */
public class Gameboard
{

    public static final int SIZE = 3;
    private Field[][] fields = new Field[SIZE][SIZE];
    private List<Field[]> lines = new ArrayList();


    public static class Position
    {
        private int row, col;

        public Position(int row, int col)
        {
            this.row = row;
            this.col = col;
        }
    }


    private static class Field
    {
        char value;

        Field(char value)
        {
            this.value = value;
        }
    }


    public Gameboard(CharSequence value)
    {
        //fill fields with characters from value
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
            {
                int i = row * SIZE + col;
                fields[row][col] = new Field(i < value.length() ? value.charAt(i) : ' ');
            }

        //create collection of all full gameboard lines
        Field[] line;

        //add all rows to lines collection
        for (int row = 0; row < SIZE; row++)
        {
            line = new Field[SIZE];
            for (int i = 0; i < SIZE; i++)
                line[i] = fields[row][i];
            lines.add(line);
        }

        //add all columns to lines collection
        for (int col = 0; col < SIZE; col++)
        {
            line = new Field[SIZE];
            for (int i = 0; i < SIZE; i++)
                line[i] = fields[i][col];
            lines.add(line);
        }

        //add both diagonals to lines collection
        line = new Field[SIZE];
        for (int i = 0; i < SIZE; i++)
            line[i] = fields[i][i];
        lines.add(line);

        line = new Field[SIZE];
        for (int i = 0; i < SIZE; i++)
            line[i] = fields[i][SIZE - i - 1];
        lines.add(line);
    }


    public CharSequence asString()
    {
        StringBuffer s = new StringBuffer();
        for (Field[] row : fields)
            for (Field field : row)
                s.append(field.value);
        return s;
    }


    public boolean doMove(Position pos, char ch)
    {
        if (pos != null && pos.row >= 0 && pos.row < SIZE && pos.col >= 0 && pos.col < SIZE)
        {
            fields[pos.row][pos.col].value = ch;
            return true;
        }
        else
            return false;
    }


    /**
     * check whether there is line filled with the same non-empty character
     * @return
     */
    public boolean isVictoryState()
    {
        lines_loop:
        for (Field[] line : lines)
        {
            if (line[0].value == ' ')
                continue;

            for (Field field : line)
                if (field.value != line[0].value)
                    continue lines_loop;

            return true; //line is found so there is state of victory
        }

        return false;
    }


    /**
     * check whether there is line that could be filled with the same non-empty character
     * @return
     */
    public boolean isDrawState()
    {
        lines_loop:
        for (Field[] line : lines)
        {
            char ch = ' ';
            for (Field field : line)
                if (field.value != ' ')
                    if (ch == ' ')
                        ch = field.value;
                    else if (field.value != ch)
                        continue lines_loop;

            return false; //line is found so there is no draw yet
        }

        return true;
    }


    public Position getRandomEmptyPosition()
    {
        List<Position> emptyPositions = new ArrayList();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (fields[i][j].value == ' ') emptyPositions.add(new Position(i, j));

        if (emptyPositions.size() == 0)
            return null;
        else
            return emptyPositions.get(new Random().nextInt(emptyPositions.size()));
    }

} //Gameboard
