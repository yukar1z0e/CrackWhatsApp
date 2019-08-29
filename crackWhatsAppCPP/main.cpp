//
// Created by yukar on 8/29/2019.
//
#include <iostream>
#include <windows.h>

using namespace std;

void writeReslutToLog(){
    string command = R"(adb logcat crackWhatsApp:D *:S *:W *:E *:F *:S -f /data/local/tmp/whatsapp.txt)";
    WinExec(command.c_str(),1);
}

void pullReslut() {
    string command = R"(adb pull /data/local/tmp/whatsapp.txt C:\Users\yukar\Desktop)";
    system(command.c_str());
}

void getResult() {
    FILE *fid = fopen("C:\\Users\\yukar\\Desktop\\whatsapp.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        cout << line << endl;
    }
}

void search(string phoneNumber) {
    string command;
    //Start WhatsApp
    command="adb shell am start -n com.whatsapp/com.whatsapp.Main";
    system(command.c_str());
    //Tap Contact Icon
    command="adb shell input tap 715 1100";
    system(command.c_str());
    //Tap Add Friend Icon
    command="adb shell input tap 200 350";
    system(command.c_str());
    //强制打开小键盘
    command="adb shell input tap 200 465";
    system(command.c_str());
    //收起小键盘
    command="adb shell input keyevent 4";
    system(command.c_str());
    //tap phone number
    command="adb shell input tap 200 690";
    system(command.c_str());
    //add phone number
    command="adb shell input text +86"+phoneNumber;
    system(command.c_str());
    //save
    command="adb shell input tap 625 100";
    system(command.c_str());
    //back
    command="adb shell input keyevent 4";
    system(command.c_str());
    //tap pic
    command = "adb shell input tap 75 490";
    system(command.c_str());
    //tap info
    command="adb shell input tap 570 795";
    system(command.c_str());
    //tap sangedian
    command="adb shell input tap 725 100";
    system(command.c_str());
    //view in address book
    command="adb shell input tap 700 295";
    system(command.c_str());
    //tap sangedian
    command="adb shell input tap 725 400";
    system(command.c_str());
    //tap delete
    command="adb shell input tap 725 490";
    system(command.c_str());
    //tap delete
    command="adb shell input tap 475 660";
    system(command.c_str());
    //back
    command="adb shell input keyevent 4";
    system(command.c_str());
    //back
    command="adb shell input keyevent 4";
    system(command.c_str());
    //force close
    command="adb shell am force-stop com.whatsapp";
    system(command.c_str());

    cout<<"继续查询"<<endl;
}

void closeKeyboard(){
    string command="adb shell input keyevent 4";
    system(command.c_str());
}

void readFile() {
    FILE *fid = fopen("C:/Users/yukar/Desktop/phonenumber.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        cout << line << endl;
    }
}

/*int main() {
    FILE *fid = fopen("C:/Users/yukar/Desktop/1.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    writeReslutToLog();
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        string phoneNumber = line;
        if (phoneNumber == "0") {
            break;
        }
        search(phoneNumber);
    }
    closeKeyboard();
    pullReslut();
    getResult();
    cout << "查询结束" << endl;
    return 0;
}*/

int main() {
    while (true)
    {
        string phoneNumber;
        cout << "输入手机号" << endl;
        cin >> phoneNumber;
        writeReslutToLog();
        if(phoneNumber=="0"){
            break;
        }
        search(phoneNumber);
        pullReslut();
        getResult();
    }
    cout<<"查询结束"<<endl;
    return 0;
}
