def num =0
new File(args[0]).eachLine { line ->
    num++;
    println "$num:$line"}
    