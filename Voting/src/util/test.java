package util;

import java.util.ArrayList;

import model.Candidate;
import dao.Dao;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Candidate> cands=new Dao().getCandidateList();
		cands.size();
	}

}
