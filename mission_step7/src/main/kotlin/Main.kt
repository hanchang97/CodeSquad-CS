fun main(args: Array<String>){
    var processList = ArrayList<ProcessData>().apply {
        add(ProcessData("A", ProcessState.READY, 3, 0))
        add(ProcessData("B", ProcessState.READY, 5, 0))
        add(ProcessData("C", ProcessState.READY, 7, 0))
        add(ProcessData("D", ProcessState.READY, 10, 0))
        add(ProcessData("E", ProcessState.READY, 15, 0))
    }



}
