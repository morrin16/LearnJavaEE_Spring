package lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Алеся on 27.10.2016.
 */
public class Gameboard
{


public static final int SIZE = 3;
private char[][] fields = new char[SIZE][SIZE];

public class Field
{
    public int row, col;
    public Field(int row, int col) { this.row = row; this.col = col;}
}


public Gameboard(CharSequence value)
{
    for (int row = 0; row < SIZE; row++)
        for (int col = 0; col < SIZE; col++)
        {
            int i = row * SIZE + col;
            fields[row][col] = i < value.length() ? value.charAt(i) : ' ';
        }
}


public CharSequence getFields()
{
    StringBuffer s = new StringBuffer();
    for (char[] row: fields)
        for (char c: row)
            s.append(c);
    return s;
}


public boolean doMove(Field field, char c)
{
    int row = field.row, col = field.col;
    fields[row][col] = c;
    
    //check row
    int i = 0;
    while (i < SIZE && fields[row][i] == c) i++;
    if (i == SIZE) return true;
    
    //check column
    i = 0;
    while (i < SIZE && fields[i][col] == c) i++;
    if (i == SIZE) return true;
    
    //check main diagonal
    if (row == col)
    {
        i = 0;
        while (i < SIZE && fields[i][i] == c) i++;
        if (i == SIZE) return true;
    }
    
    //check other diagonal
    if (row + col == SIZE-1)
    {
        i = 0;
        while (i < SIZE && fields[i][SIZE-1 - i] == c) i++;
        if (i == SIZE) return true;
    }

    return false;
}


public Field randomEmpty()
{
    List<Field> emptyFields = new ArrayList();
    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            if (fields[i][j] == ' ') emptyFields.add(new Field(i, j));
    
    if (emptyFields.size() == 0)
        return null;
    else
        return emptyFields.get(new Random().nextInt(emptyFields.size()));
}


public boolean isDraw()
{
    Character ch;
    
    //check rows
    rows:
    for (int i = 0; i < SIZE; i++)
    {
        ch = ' ';
        for (int j = 0; j < SIZE; j++)
            if (differenceFound(ch, fields[i][j]))
                continue rows;
            
        return false;
    }
        
    //check columns
    columns:
    for (int i = 0; i < SIZE; i++)
    {
        ch = ' ';
        for (int j = 0; j < SIZE; j++)
            if (differenceFound(ch, fields[j][i]))
                continue columns;
    
        return false;
    }
    
    //check main diagonal
    main_diag:
    {
        ch = ' ';
        for (int i = 0; i < SIZE; i++)
            if (differenceFound(ch, fields[i][i]))
                break main_diag;
        
        return false;
    }
    
    //check other diagonal
    other_diag:
    {
        ch = ' ';
        for (int i = 0; i < SIZE; i++)
            if (differenceFound(ch, fields[i][SIZE-1 - i]))
                break other_diag;
        
        return false;
    }
    
    //all possible lines contain different characters
    //so no one can be filled by the same character
    return true;
}


private boolean differenceFound(Character ch, char field)
{
    if (ch == ' ')
    {
        ch = field;
        return false;
    }
    else
        return (field != ' ' && field != ch);
}


} //Gameboard
