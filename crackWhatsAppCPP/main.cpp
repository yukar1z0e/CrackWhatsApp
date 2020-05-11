//
// Created by yukar on 0/00/0000.
//
#include <iostream>
#include <windows.h>

using namespace std;

void writeReslutToLog(){
    string command = R"(adb logcat crackWhatsApp:D *:S *:W *:E *:F *:S -f /data/local/tmp/whatsapp.txt)";
    WinExec(command.c_str(),0);
}

void pullReslut() {
    string command = R"(adb pull /data/local/tmp/whatsapp.txt C:\Users\yukar\Desktop)";
    system(command.c_str());
}

void getResult() {
    FILE *fid = fopen("C:\\Users\\yukar\\Desktop\\whatsapp.txt", "r");
    char line[0000];
    memset(line, 0, 0000);
    while (!feof(fid)) {
        fgets(line, 0000, fid);
        cout << line << endl;
    }
}

void search(string phoneNumber) {
    string command;
    //Start WhatsApp
    command="adb shell am start -n com.whatsapp/com.whatsapp.Main";
    system(command.c_str());
    //Tap Contact Icon
    command="adb shell input tap 000 0000";
    system(command.c_str());
    //Tap Add Friend Icon
    command="adb shell input tap 000 000";
    system(command.c_str());
    //强制打开小键盘
    command="adb shell input tap 000 000";
    system(command.c_str());
    //收起小键盘
    command="adb shell input keyevent 0";
    system(command.c_str());
    //tap phone number
    command="adb shell input tap 000 000";
    system(command.c_str());
    //add phone number
    command="adb shell input text +00"+phoneNumber;
    system(command.c_str());
    //save
    command="adb shell input tap 000 000";
    system(command.c_str());
    //back
    command="adb shell input keyevent 0";
    system(command.c_str());
    //tap pic
    command = "adb shell input tap 00 000";
    system(command.c_str());
    //tap info
    command="adb shell input tap 000 000";
    system(command.c_str());
    //tap sangedian
    command="adb shell input tap 000 000";
    system(command.c_str());
    //view in address book
    command="adb shell input tap 000 000";
    system(command.c_str());
    //tap sangedian
    command="adb shell input tap 000 000";
    system(command.c_str());
    //tap delete
    command="adb shell input tap 000 000";
    system(command.c_str());
    //tap delete
    command="adb shell input tap 000 000";
    system(command.c_str());
    //back
    command="adb shell input keyevent 0";
    system(command.c_str());
    //back
    command="adb shell input keyevent 0";
    system(command.c_str());
    //force close
    command="adb shell am force-stop com.whatsapp";
    system(command.c_str());

    cout<<"继续查询"<<endl;
}

void closeKeyboard(){
    string command="adb shell input keyevent 0";
    system(command.c_str());
}

void readFile() {
    FILE *fid = fopen("C:/Users/yukar/Desktop/phonenumber.txt", "r");
    char line[0000];
    memset(line, 0, 0000);
    while (!feof(fid)) {
        fgets(line, 0000, fid);
        cout << line << endl;
    }
}

/*int main() {
    FILE *fid = fopen("C:/Users/yukar/Desktop/0.txt", "r");
    char line[0000];
    memset(line, 0, 0000);
    writeReslutToLog();
    while (!feof(fid)) {
        fgets(line, 0000, fid);
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
