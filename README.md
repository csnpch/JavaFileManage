# JavaFileManage
### Easy to use file by Class ManageFile :)
#### For manage mini file eg. -> (.txt, .md)
#### Your can look example how to use in ----> Main.java


<br><br>
#### contructor parameter
(nameFile)
(nameFile, typeFile)
- example to keep values:  ManageFile [variable] = new ManageFile(..1.or.2.agrs..);


<br><br>
#### InsertData by Array or String
- String[] strData = {"val1", "val2", "val3"}
array -> [variable].writeDataByArr(strData)
<br>
string -> [variable].writeDataByStr("val1, val2, ...")


<br><br>
#### ClearDataInFile and Insert New for update data or delete in variable local
[variable].writeDataNewToFile(data) : void String[][]


<br><br>
#### getData
getData() : void 
- example to keep values:  String[][] nameVal = [variable].getData();

<br><br>
#### Example value in file (no encode) 
1,SomeValue,9999,244<br>
2,SomeValu2,8888,442
<br><br>
if use manual insert data to file 
you should uncomment return encode, decode function ;-; 
