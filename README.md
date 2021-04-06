### Build 16 - bit modern computer from NAND gate, for reference, please do not copy
### Assember usage
流程
1.创建符号表，初始化将预声明符号填入
1.第一遍扫描寻找声明标签，如果声明没有错误，将其填入符号表，在整个过程中记录读的行数（除去声明标签和空格）
2.第二遍扫描整个程序，如果需要变量，查表，如果没找到，说明是新变量，添入符号表
3.编译完成，删除符号表![Uploading image.png…]()

