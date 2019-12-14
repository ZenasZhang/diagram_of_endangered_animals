It is my NO.3 coursework of CSE105 (Introduction to Programming in Java) in XJTLU.

The task is to:
    Create a Java application which displays a pictograph for a given data set. You
    should create a relevant dataset, choose appropriate images, and design and
    code an application to display this information. You should also write a short
    report. 

Examples can be found at:
    Further information and examples can be found at
    https://vc012.k12.sd.us/Graphs/picto1.html
	
Further requirements:
    You should use your graphics and programming knowledge to create your own,
    given your chosen dataset. Your design and colour should be unique to you, and
    you should pay attention to OOP principles as taught this semester. You may reuse and re-purpose code and classes developed and used (by you) in the Labs.
    
	Dataset
        Your dataset must be related to the number of endangered animals living in a
        number of countries/regions. You should research and choose/create your own
        dataset.
            Possible animals: lions, elephants, tigers.
            The data sets will contain from 5 to 10 countries (inclusive).
            The data will be contained in a plain text .txt file in CSV format (See
            appendix for example)
			

This project is my solution.
    My project can create three pictures to show three different kinds of
    endangered animals by creating objects of DrawAsianElephant,
    DrawSnowLeopard and DrawChimpanzee. These three classes extend
    ReadAndCreate. Their functions are to use specific information of each animal
    e.g. data path, image path and value of key) as parameters of super class
    methods, and then use these methods to create picture.
	
	The most important class is ReadAndCreate, it extends JFrame and implements
    Readable.
    First, it has read() by implementing Readable. This method can read csv file
    and get a Map and an ArrayList stores key of the Map.
    Then, it has create() method to create JFrame. I used GridBagLayout in my
    project. It is a grid layout but one component can occupy more than one grid.

Reference:
    Java Platform, Standard Edition 8 API
	Available: https://docs.oracle.com/javase/8/docs/api/