### Build 16 - bit modern computer from NAND gate, for reference, please do not copy
### Assember rule
#### SymbolTable Class&emsp;：restore symbol
#### Parser Class&emsp;&emsp;&emsp;&emsp;：get the different commands and breaks them into parts
#### Code Class&emsp;&emsp;&emsp;&emsp;：convert instruction to binary
#### Compiler Class&emsp;&emsp;：Main loop，read the file
### Assember usage
- 创建符号表，将预声明符号填入(初始化)
- 第一遍整体扫描，寻找声明标签，如果声明没有错误，将其填入符号表，在整个过程中记录读的指令行数（方便找标签对应的地址）
- 第二遍扫描并编译，遇到@变量，查表，如果没找到，说明是新变量，添入符号表，并分配内存
- 编译完成，删除符号表

