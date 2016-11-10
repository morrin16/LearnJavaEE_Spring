package lesson4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("gameboard.go")
public class GameController
{


@RequestMapping (method = RequestMethod.GET)
protected String requestGet(Model model,
  @RequestParam(value="yourWins", required=false, defaultValue="0") int playerWins,
  @RequestParam(value="AIWins", required=false, defaultValue="0") int compWins)
{
    model.addAttribute("gameboard", "         ");
    model.addAttribute("yourWins", playerWins);
    model.addAttribute("AIWins", compWins);
    return "gameboard.jsp";
}


@RequestMapping (method = RequestMethod.POST)
protected String requestPost(@RequestParam("gameboard") String gameboard,
  @RequestParam("row") int row, @RequestParam("col") int col,
  @RequestParam("yourWins") int playerWins, @RequestParam("AIWins") int compWins,
  Model model)
{
    Gameboard gb = new Gameboard(gameboard);
    boolean playerIsWinning = gb.doMove(gb.new Field(row, col), 'X');
    boolean compIsWinning = false;
    
    if (playerIsWinning)
        playerWins++;
    else if (!gb.isDraw())
    {
        Gameboard.Field field = gb.randomEmpty();
        if (field != null)
            compIsWinning = gb.doMove(field, '0');
        if (compIsWinning)
            compWins++;
    }
    
    model.addAttribute("gameboard", gb.getFields().toString());
    model.addAttribute("yourWins", playerWins);
    model.addAttribute("AIWins", compWins);
    model.addAttribute("gameover", playerIsWinning || compIsWinning || gb.isDraw());
    return "gameboard.jsp";
}
    
    
} //GameController
