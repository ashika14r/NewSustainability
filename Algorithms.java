package com.example.newsustainability;

public class Algorithms {

    public static double giveMonthlyRecommendation(double goalValueConsumption, double currentValueConsumption, int daysLeft) {
        // this method is used to generate a daily consumption value based on current consumption
        // it can be used for both electricity or water, maybe gasoline as well
        // it will be modified further to provide more comprehensive functionality
        if (currentValueConsumption > goalValueConsumption) {
            System.out.println("You've exceeded your expected amount of consumption! Try consume as little as possible until the end of the month! ");
            return 0;
        } else {
            System.out.println("Continue your sustainable lifestyle based on the following instruction to meet your monthly goal! ");
            return ((goalValueConsumption - currentValueConsumption) / daysLeft);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public static boolean checkAchievement(double achievementValue, double currentRelevantValue, date thisDay) {
        // this method is used to check if an achievement has been reached
        // this methods should be modified based on the structure of the Achievement table in our database
        if (currentRelevantValue > achievementValue) {
            // we need more detailed conditional statement to complete this method
            System.out.println("Achieved on " + thisDay);
            return true;
            // also need to add a statement to change the badge color
        } else {
            System.out.println("Not yet achieved! ");
            return false;
        }
    }



}
