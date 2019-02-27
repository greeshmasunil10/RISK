package view;
import java.util.*;

import controller.AttackController;
import model.Country;
import model.Player;

import java.io.*;
public class AttackerButtons {
	AttackController attackController = new AttackController();
	public void endReinforcementsPhaseButton(Player player){
		//call getMyCountries(player) method and display countries
	}
	public void endAttackPhaseButton(Player player) {
		//call fortification controller
	}
	public void attackButton(Country attacker, Country defender) {
		if(attacker.getNoOfArmies()>=2 && defender.getNoOfArmies()!=0)
		{
			//display the number of attacker dice
			int attackerDice=attackController.setNoOfDice(attacker, "A");
			//display the number of defender dice
			int defenderDice=attackController.setNoOfDice(defender, "D");
			List<Integer> attackerDiceRoll= new ArrayList<Integer>();
			List<Integer> defenderDiceRoll= new ArrayList<Integer>();
			//display the int list values as the results from dice roll
			for(int i=0;i<attackerDice;i++) {
				attackerDiceRoll.add(attackController.rollDice());
			}
			for(int i=0;i<defenderDice;i++) {
				defenderDiceRoll.add(attackController.rollDice());
			}
			for(int i=0;i<defenderDice;i++) {
				int attackerMax=getMaxValue(attackerDiceRoll);
				int defenderMax=getMaxValue(defenderDiceRoll);
				if(attackerMax<=defenderMax) {
					attackController.updateArmies(attacker);
				}
				else {
					attackController.updateArmies(defender);
				}
				attackerDiceRoll.remove(attackerDiceRoll.indexOf(attackerMax));
				defenderDiceRoll.remove(defenderDiceRoll.indexOf(defenderMax));
				if(attackerDice==1)
					break;
				else
					continue;
			}
			if(defender.getNoOfArmies()==0) {
				attackController.updateOwner(defender, attacker.getOwner());
				//call place armies method from reinforcements phase and force player to place armies
				//that are at least the number of dice rolled by attacker
			}
			if(attackController.getMyCountries(defender.getOwner()).size()==0) {
				//add code to give all defenders cards to attacker
			}
		}
		else
		{
			//tell the player that you must have at least 2 armies in the country to attack
			//and show his countries again
		}
	}
	public int getMaxValue(List<Integer> list) {
		int max=list.get(0);
		for(int i=1;i<list.size();i++) {
			if(list.get(i)>max)
				max=list.get(i);
			else
				continue;
		}
		return max;
	}
	public void endAttackButton() {
		//display players countries again for another attack
	}
}
