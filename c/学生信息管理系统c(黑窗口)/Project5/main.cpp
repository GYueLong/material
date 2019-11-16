#pragma warning(disable:4996);
//vs2015中不支持scanf;添加此语句便可使用scanf
//在vc6.0或dev c++中直接注释掉这条即可
/*
	学生证管理程序
	2019-11-11
	gao
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct StudentMessage//学生信息结点
{
	char name[20];
	char gnder[20];
	int  id;					//学号
	char college[20];			//学院
	char major[20];				//专业
	char classname[20];			//班级
	char birthday[20];			//出生年月
	int AdmissionTime;		    //入学时间
	char DrivingRange[20];		//乘车区间
	struct StudentMessage *next;	//链表
}Stu;

Stu head;//定义头结点

/*统计班级人数、男女比例*/
/*这里需要优化，若不从窗口输入，怎么统计每个班（有几个班，班名各是什么都不知道）*/
void Statistical()
{
	Stu *p;
	p = head.next;
	int sum=0, man=0, women=0;
	char ClassName[20];
	if (p == NULL) {
		printf("请添加信息！！！\n");
		return;
	}
	printf("请输入要统计的班名:\n");
	scanf("%s",ClassName);
	while (p != NULL) {
		if (strcmp(p->classname, ClassName) == 0) {
			sum++;//总和++
			if (strcmp(p->gnder, "男") == 0) {
				man++;
			}
			else {
				women++;
			}
		}
		p = p->next;
	}
	printf("%s班共 %d 人 \t男:女\t%d:%d\n",ClassName,sum,man,women);
	return;
}

/*信息读入*/
void InFile()
{
	FILE *fp;
	Stu *p,*tail;
	tail = &head;
	char filename[20] = "StudentMessage.txt";
	char kong[20];
	fp = fopen(filename,"r");
	if (fp == NULL) {
		printf("文件打开失败！！！\n");
		return;
	}
	while(!feof(fp)){
		p = (Stu *)malloc(sizeof(Stu));
		fscanf(fp,"%s",p->name);
		if (strcmp(p->name, "") == 0) {
			printf("没有信息，请添加！！！\n");
			return;
		}
		fscanf(fp,"%s",p->gnder);
		fscanf(fp,"%d",&p->id);
		fscanf(fp,"%s",p->college);
		fscanf(fp,"%s",p->major);
		fscanf(fp,"%s",p->classname);
		fscanf(fp,"%s",p->birthday);
		fscanf(fp,"%d",&p->AdmissionTime);
		fscanf(fp,"%s",p->DrivingRange);

	tail->next = p;
	p->next = NULL;
	tail = p;
}

	fclose(fp);
	printf("信息导入成功！！！\n");
	return;
}

/*信息写入*/
void OutFile()
{
	FILE *fp;
	Stu *p;
	p = head.next;
	if (p == NULL) {
		printf("请添加学生信息！！！\n");
		return;
	}
	char filename[20] = "StudentMessage.txt";//更改文件名
	fp = fopen(filename,"a");//以a打开文件，在文件末尾追加内容
	if (fp == NULL) {
		printf("文件打开失败！！！\n");
		return;
	}
	while (p!=NULL) {
		fprintf(fp,"%s\t%s\t%d\t%s\t%s\t%s\t%s\t%d\t%s", p->name, p->gnder, p->id, p->college, p->major, p->classname, p->birthday, p->AdmissionTime, p->DrivingRange);
		p = p->next;
		fprintf(fp,"\n");
	}
	fclose(fp);
	printf("保存成功!!!\n");
	return;
}
/*查看全校学生信息*/
void AllStudentMessage() {
	Stu *p;
	p = head.next;
	if (p == NULL) {
		printf("没有学生信息请添加！！！\n");
	}
	else {
		printf("姓名\t  性别\t    学号\t学院\t  专业\t    班级\t  出生年月\t入学时间\t乘车区间\n");
	}
	while (p != NULL) {		
		printf("%-10s%-10s%-10d  %-10s%-10s%-10s\t%-10s\t%-10d\t%-10s\n", p->name, p->gnder, p->id, p->college, p->major, p->classname, p->birthday, p->AdmissionTime, p->DrivingRange);
		p = p->next;
	}
	return;
}

/*添加学生信息*/
void AddStudentMessage()
{
	Stu *tail,*p,*q;
	tail = &head;
	while (tail->next != NULL) {//再次添加时，如果之前添加过则tail->next不为空，把tail移到尾继续添加新数据
		tail = tail->next;
	}
	p = (Stu *)malloc(sizeof(Stu));
	if (p == NULL) {
		printf("学生信息结点创建失败，请重试");
		return;
	}
	q = head.next;
	fflush(stdin);//清除缓存区
	printf("请输入学生姓名:\n");
	scanf("%s",p->name);
	fflush(stdin);
	printf("性别:\n");
	scanf("%s",p->gnder);
	fflush(stdin);
	printf("学号:\n");
	scanf("%d",&(p->id));

	/*验证学号的唯一性*/
	while (q != NULL) {
		if (p->id == q->id) {
			printf("学号已存在，添加失败！！！\n");
			return;
		}
		q = q->next;
	}
	fflush(stdin);
	printf("学院:\n");
	scanf("%s",p->college);
	fflush(stdin);
	printf("专业:\n");
	scanf("%s",p->major);
	fflush(stdin);
	printf("班级:\n");
	scanf("%s",p->classname);
	fflush(stdin);
	printf("出生年月:(xxxx-xx-xx例2019年1月1日:2019-01-01)\n");
	scanf("%s",p->birthday);
	/*出生年月的合法性检验 时间格式:xxxx-xx-xx*/
	while (p->birthday[4] != '-'||p->birthday[7]!='-') {
		printf("请重新输入:\n");
		scanf("%s", p->birthday);
	}
	fflush(stdin);
	printf("入学时间:(2000-3000)\n");
	scanf("%d",&p->AdmissionTime);
	/*入学时间的合法性检验 2000<=入学时间<=3000*/
	while (2000>p->AdmissionTime||p->AdmissionTime>3000)
	{
		printf("请重新输入:\n");
		scanf("%d", &p->AdmissionTime);
	}
	fflush(stdin);
	printf("乘车区间:\n");
	scanf("%s",p->DrivingRange);
	fflush(stdin);

	tail->next = p;
	p->next = NULL;
	tail = p;
	return;
}

/*查找学生信息*/
void FindStudentMessage()
{
	Stu *p;
	p = head.next;
	int No=0;	
	printf("请输入要查询的学生学号:\n");
	scanf("%d",&No);
	printf("姓名\t性别\t学号\t学院\t专业\t班级\t出生年月\t入学时间\t乘车区间\n");
	while (p != NULL) {		
		if (p->id == No) {
			printf("%s\t%s\t%d\t%s\t%s\t%s\t%s\t%d\t%s\n",p->name,p->gnder,p->id,p->college,p->major,p->classname,p->birthday,p->AdmissionTime,p->DrivingRange);
		}
		p = p->next;
	}
	return;
}

/*查询班级信息*/
void FindClassMessage()
{
	Stu *p;
	p = head.next;
	char ClassNo[20]="0";
	printf("请输入要查询的班级班号:\n");
	scanf("%s", ClassNo);
	printf("%s 班的学生信息如下:\n", ClassNo);	
	printf("班级\t姓名\t性别\t学号\t学院\t专业\t出生年月\t入学时间\t乘车区间\n");
	while (p != NULL) {
		//这里if判断用strcmp
		if (strcmp(p->classname,ClassNo)==0) {
			printf("%s\t%s\t%s\t%d\t%s\t%s\t%s\t%d\t%s\n", p->classname ,p->name, p->major, p->id, p->college, p->major, p->birthday, p->AdmissionTime, p->DrivingRange);
		}
		p = p->next;
	}
	return;
}

/*修改学生信息*/
void ModifyStudentMessage()//修改学生信息
{
	Stu *p;
	p = head.next;
	int no = 0,flag=0,NO=0;//flag判断学生是否存在
	printf("请输入要修改学生的学号:\n");
	scanf("%d",&no);
	while (p != NULL) {
		if (p->id == no) {
			flag = 1;
			break;
		}
		p = p->next;
	}
	if (flag == 0) {
		printf("%d该学生不存在，不能修改！！！\n",no);
		return;
	}

	system("cls");
	printf("**********修改 %d 学生信息**********\n",no);
	printf("修改编号:\n");
	printf("\t0.修改姓名\n");
	printf("\t1.修改性别\n");
	printf("\t2.修改学号\n");
	printf("\t3.修改学院\n");
	printf("\t4.修改专业\n");
	printf("\t5.修改班级\n");
	printf("\t6.修改出生年月\n");
	printf("\t7.修改入学时间\n");
	printf("\t8.修改乘车区间\n");

	printf("请输入你要修改的信息编号:\n");
	scanf("%d",&NO);
	switch (NO) {
	case 0:printf("修改前 %s\n修改为: ",p->name);
		   scanf("%s",p->name);
		break;
	case 1:printf("修改前 %s\n修改为: ",p->gnder);
		   scanf("%s",p->gnder);
		break;
	case 2:printf("修改前 %d\n修改为: ",p->id);
		scanf("%d",&p->id);
		break;
	case 3:printf("修改前 %s\n修改为: ", p->college);
		scanf("%s",p->college);
		break;
	case 4:printf("修改前 %s\n修改为: ", p->major);
		scanf("%s",p->major);
		break;
	case 5:printf("修改前 %s\n修改为: ", p->classname);
		scanf("%s",p->classname);
	    break;
	case 6:printf("修改前 %s\n修改为: ", p->birthday);
		scanf("%s",p->birthday);
		break;
	case 7:printf("修改前 %d\n修改为: ", p->AdmissionTime);
		scanf("%d",&p->AdmissionTime);
		break;
	case 8:printf("修改前 %s\n修改为: ", p->DrivingRange);
		scanf("%s",p->DrivingRange);
		break;
	}
	printf("修改完成！！！\n");
	return;
}

/*删除学生信息*/
void DeleteStudentMessage()
{
	Stu *p,*pre;
	p = head.next;
	pre = head.next;
	if (p == NULL) {
		printf("没有学生信息，请添加！！！\n");
		return;
	}
	int no = 0,flag=0;//flag是用来记录要删除的结点之前的结点
	printf("请输入要删除学生的学号：\n");
	scanf("%d",&no);
	while (p != NULL) {
		if (p->id == no) {
			break;
		}
		p = p->next;
		flag++;
	}
	if (flag == 0) {//删除head后面的结点的处理程序
		head.next = p->next;
		free(p);
		return;
	}
	for (int i = 0; i < flag-1; i++) {
		pre = pre->next;
	}
	pre->next = p->next;
	free(p);
	if (p == NULL) {
		printf("%d同学删除成功",no);
	}
	else {
		printf("%d同学没有删除",no);
	}
	return;
}

/*释放链表中所有存放学生信息的结点*/
void freeList()
{
	Stu *p;
	p = head.next;
	while (p != NULL) {
		head.next = p->next;
		free(p);	/*释放p指向的结点空间*/
		p = head.next;/*指针变量p指向下一个待释放的结点*/
	}
}

/*主页面菜单*/
void MainMenu()//主菜单"\t制表符"
{
	printf("***************学生证管理程序*****************\n");
	printf("\t0.查看全校学生\n");
	printf("\t1.添加学生信息\n");
	printf("\t2.查询学生信息\n");
	printf("\t3.查询班级信息\n");
	printf("\t4.修改学生信息\n");
	printf("\t5.删除学生信息\n");
	printf("扩展:\n");
	printf("\t6.统计班级人数、男女比例\n");
	printf("\t7.信息读入（文件读入）\n");
	printf("\t8.信息保存（文件写入）\n");
	printf("\t9.退出程序\n");
	printf("**********************************************\n");
}

/*主函数，程序入口*/
void main()
{
	int no = 0;//功能选项
	while (1) {
		system("cls");//清屏
		MainMenu();
		printf("请输入功能选项:\n");
		scanf("%d",&no);
		switch (no) {
		case 0:AllStudentMessage();
			break;
		case 1:AddStudentMessage();
			break;
		case 2:FindStudentMessage();
			break;
		case 3:FindClassMessage();
			break;
		case 4:ModifyStudentMessage();
			break;
		case 5:DeleteStudentMessage();
			break;
		case 6:Statistical();
			break;
		case 7:InFile();
			break;
		case 8:OutFile();
			break;
		case 9:freeList(); return;
		default:printf("选择错误\n");
		}
		system("pause");
	}	
	return;
}