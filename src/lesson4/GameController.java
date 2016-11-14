package lesson4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("gameboard.go")
public class GameController
{

    @RequestMapping(method = RequestMethod.GET)
    protected String requestGet(Model model,
      @RequestParam(value = "wins", required = false, defaultValue = "0") int playerWins,
      @RequestParam(value = "losses", required = false, defaultValue = "0") int playerLosses)
    {
        model.addAttribute("gameboard", new Gameboard("").asString());
        model.addAttribute("wins", playerWins);
        model.addAttribute("losses", playerLosses);
        return "gameboard.jsp";
    }


    @RequestMapping(method = RequestMethod.POST)
    protected String requestPost(Model model,
      @RequestParam("gameboard") String gameboard, @RequestParam("gameover") boolean gameover,
      @RequestParam("wins") int playerWins, @RequestParam("losses") int playerLosses,
      @RequestParam("row") int row, @RequestParam("col") int col)
    {
        if (gameover)
            return requestGet(model, playerWins, playerLosses);

        Gameboard gb = new Gameboard(gameboard);

        gb.doMove(new Gameboard.Position(row, col), 'X');
        gameover = gb.isVictoryState();

        if (gameover)
        {
            playerWins++;
            model.addAttribute("alertMessage", "You win!");
        }
        else
            gameover = gb.isDrawState();

        if (!gameover)
        {
            gb.doMove(gb.getRandomEmptyPosition(), '0');
            gameover = gb.isVictoryState();

            if (gameover)
            {
                playerLosses++;
                model.addAttribute("alertMessage", "You loss!");
            }
            else
                gameover = gb.isDrawState();
        }

        model.addAttribute("gameboard", gb.asString());
        model.addAttribute("wins", playerWins);
        model.addAttribute("losses", playerLosses);
        model.addAttribute("gameover", gameover);

        if (gameover && !model.containsAttribute("alertMessage"))
            model.addAttribute("alertMessage", "It is draw!");

        return "gameboard.jsp";
    }

} //GameController
