package ticTacToe.v280.controllers;

import ticTacToe.v280.models.Game;
import ticTacToe.v280.models.Coordinate;
import ticTacToe.v280.utils.IO;

public abstract class MoveController extends ColocateController {

	private Coordinate origin;

	public MoveController(Game game) {
		super(game, "Mueve");
	}

	@Override
	protected void colocate() {
		this.remove();
		this.put("A");
	}
	
	private void remove(){
		Error error;
		do {
			origin = this.selectOrigin();
			error = this.validateOrigin();
			if (error != null){
				new IO().writeln(""+error);
			}
		} while (error != null);	
		this.getBoard().remove(origin, this.getTurn().take());
	}
	
	protected abstract Coordinate selectOrigin();

	private Error validateOrigin() {
		if (!this.getBoard().full(origin, this.getTurn().take())) {
			return Error.NOT_PROPERTY;
		}
		return null;
	}
	
	@Override
	protected Error validateTarget() {
		Error error = super.validateTarget();
		if (error != null) {
			return error;
		}
		if (origin.equals(this.getTarget())) {
				return Error.REPEATED_COORDINATE;
		}
		return null;
	}	
	
	public Coordinate getOrigin() {
		return origin;
	}
	
}
