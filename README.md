#Running the program


From jar.: Simply open the provided jar file

From eclipse: Run the main program located in QuantumGuiFrame as a Java Application
(note: You may need to set import the JFreeChart .jar file to do this. See https://github.com/wmcnicho/QCP_SP2015/issues/11 for more information)

#  Basic Usage 

*   Use the drop down menu to the left to choose between running Shor's or Grover's algorithm.
*   Select the proper input value(s) for the simulation.
*   Select the desired gate representation
*   Press the start button

##  Grover's Algorithm

In order to start a Grover's simulation, import a txt file (via File->Open File) with comma separated numbers (i.e. 3,14,157,9,...) and a enter value to search for. Test data is provided in the 'tests/' directory.

##  Shor's Algorithm

To use Shor's input an integer between 2 to 1023 and it will be factorized.

Note: Shor's Algorithm will occasionally return obvious factors (i.e. the input number and  1) due to the inherent randomness of the algorithm. Run the simulation again if such results occur.

## Gate Representation

For best results use the 'Functional' gate representation. Sparse Matrix works slightly faster than Functional (and Dense Matrix) but can not run on as many qubits.

##JFreeChart
This program uses the JFreeChart open source library for it's graph outputs. See http://www.jfree.org/jfreechart/ for more information.
