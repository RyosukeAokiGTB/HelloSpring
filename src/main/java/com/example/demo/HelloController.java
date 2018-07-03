package com.example.demo;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "input your name :");    // 表示メッセージ
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView send(@RequestParam("name")String name,
            ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "Hello " + name + " !");    // 表示メッセージ
        mav.addObject("value", name);                    // 入力テキストに入力値を表示
        return mav;
    }

    @RequestMapping(value="/game", method=RequestMethod.GET)
    public ModelAndView game(ModelAndView mav) {
        mav.setViewName("gameview");
        return mav;
    }

    @RequestMapping(value="/game", method=RequestMethod.POST)
    public ModelAndView gamesend(@RequestParam("name")String hand, ModelAndView mav) {
        mav.setViewName("gameview");
        mav.addObject("msg", hand);    // 表示メッセージ

        int judge = judgment(hand);
        String result;
        switch(judge) {
        case 0:
        	result = "draw.";
        	break;
        case 1:
        	result = "you lose...";
        	break;
        case 2:
        	result = "you win!!";
        	break;
        default:
    		result = "please confirm your hand.";
    		break;
        }

        mav.addObject("resultmsg",result);

        return mav;
    }

    public int judgment(String hand) {
    	int numHand;
    	switch(hand) {
        case "rock":
        	numHand = 0;
        	break;
        case "scissors":
        	numHand = 1;
        	break;
        case "paper":
        	numHand = 2;
        	break;
        default:
        	return 3;
        }

    	Random rand = new Random();
        int cpuhand = rand.nextInt(3);
        int result = (numHand - cpuhand + 3) % 3;

        return result;
    }

}

//branchに分けるtest...