package com.sh.controller;

import com.sh.controller.action.*;

public class ActionFactory {
		private static ActionFactory instance = new ActionFactory();
		
		private ActionFactory() {
			super();
		}
		
		public static ActionFactory getInstance() {
			return instance;
		}
		
		public Action getAction(String command) {
			Action action = null;
			
			if(command.equals("board_list")) {
				action = new BoarListAction();
			}
			else if(command.equals("board_write_form")) {
				action = new BoardWriteFormAction();
			}
			else if(command.equals("board_write")) {
				action = new BoardWriteAction();
			}
			else if(command.equals("board_view")) {
				action = new BoardViewAction();
			}
			else if(command.equals("board_check_pass_form")) {
				action = new BoardCheckPassFormAction();
			}
			else if(command.equals("board_check_pass")) {
				action = new BoardCheckPass();
			}
			else if(command.equals("board_delete")) {
				action = new BoardDelete();
			}
			else if(command.equals("board_update_form")) {
				action = new BoardUpdateFormAction();
			}
			else if(command.equals("board_update")) {
				action = new BoardUpdateAction();
			}
			else if(command.equals("comment_write")) {
				action = new CommentWrite();
			}
			
			return action;
		}
}
