package com.toYou.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@Controller
public class BoardController {

	@GetMapping("/list")
	public String board() {
		return "board_list";
	}
	
}
