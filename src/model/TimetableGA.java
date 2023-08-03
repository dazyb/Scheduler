 package model;

import javax.swing.JOptionPane;

public class TimetableGA {
	
	public static void schedule() {
		// Get a Timetable object with all the available information.
        Timetable timetable = initializeTimetable();
        
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        
        // Initialize population
        Population population = ga.initPopulation(timetable);
        
        // Evaluate population
        ga.evalPopulation(population, timetable);
        
        // Keep track of current generation
        int generation = 1;
        
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, 1000) == false
            && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
//            System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());

            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // Evaluate population
            ga.evalPopulation(population, timetable);

            // Increment the current generation
            generation++;
        }

        // Print fitness
        timetable.createClasses(population.getFittest(0));
        JOptionPane.showMessageDialog(null,"Solution found in " + generation + " generations\n"+"Final solution fitness: " + population.getFittest(0).getFitness()
        		+"\nClashes: " + timetable.calcClashes());

        // Print classes
        System.out.println();
        Class classes[] = timetable.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
        	ScheduleTableDB.new_schedule(timetable.getModule(bestClass.getModuleId()).getModuleName(), timetable.getModule(bestClass.getModuleId()).getModuleCode(),
        			timetable.getProfessor(bestClass.getProfessorId()).getProfessorName(), timetable.getRoom(bestClass.getRoomId()).getRoomNumber(), 
        			timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot()
        			,SecondSemesterTableDB.getdept(timetable.getModule(bestClass.getModuleId()).getModuleCode(), timetable.getModule(bestClass.getModuleId()).getModuleId()),
        			SecondSemesterTableDB.getstudentLevel(timetable.getModule(bestClass.getModuleId()).getModuleCode()),
        			SecondSemesterTableDB.getlecturerInitials(timetable.getModule(bestClass.getModuleId()).getModuleCode(), timetable.getModule(bestClass.getModuleId()).getModuleId())
        			);
            classIndex++;
        }

	}
	
//	public static void main(String[] args) {
//			schedule();
//    	    }

    /**
     * Creates a Timetable with all the necessary course information.
     * 
     * Normally you'd get this info from a database.
     * 
     * @return
     */
	private static Timetable initializeTimetable() {
		// Create timetable
		Timetable timetable = new Timetable();

		// Set up rooms
		SecondSemesterTableDB.rooms(timetable);

		// Set up timeslots
		SecondSemesterTableDB.timeslot(timetable);

		//Set up professors
		SecondSemesterTableDB.lecturer(timetable);

		// Set up modules and define the professors that teach them
		SecondSemesterTableDB.course(timetable);

		// Set up student groups and the modules they take.
		SecondSemesterTableDB.students(timetable);
		return timetable;
	}
	
//	public static void main(String[] args) {
//		schedule();
//	}

}
