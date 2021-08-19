#include <jni.h>
#include <string>


JNIEXPORT jint JNICALL JNI_OnLoad (JavaVM* vm,void* reserved){

    JNIEnv* env = NULL;
    jint result = -1;
    if(vm->GetEnv((void**)&env, JNI_VERSION_1_4) != JNI_OK)
        return result;

    return JNI_VERSION_1_4;
}


extern "C"{
    JNIEXPORT jboolean JNICALL Java_com_isccohhh_MainActivity_check1(JNIEnv *env, jobject thiz, jbyteArray data){
        int lenData = env->GetArrayLength(data);
        if(lenData != 6)
            return (jboolean)false;
        auto* d = (char*)(env->GetByteArrayElements(data, 0));
        for(int i=0; i<lenData; i++){
            if((int)d[i] < 97 || (int)d[i] > 122){
                return (jboolean)false;
            }
        }
        return (jboolean)true;
    }
}


//
//extern "C" {
//    JNIEXPORT jboolean JNICALL Java_com_isccohhh_MainActivity_check111(JNIEnv *env, jobject thiz, jbyteArray data){
//        /*char[] chArray = s.toCharArray();
//        if(s.length() != 6)
//            return false;
//        for (char c : chArray) {
//            if ((int) c < 97 || (int) c > 122) {
//                return false;
//            }
//        }
//        return true;*/
//        int lenData = env->GetArrayLength(data);
//        if(lenData != 6)
//            return false;
//        auto* d = reinterpret_cast<uint8_t *>(env->GetByteArrayElements(data, 0));
//        for(int i=0; i<lenData; i++){
//            if((int)d[i] < 97 || (int)d[i] > 122){
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//
//    JNIEXPORT jstring JNICALL Java_com_isccohhh_MainActivity_check222(JNIEnv *env, jobject thiz, jbyteArray data){
//        /*
//         int count = 0;
//            int sumLocation = 0;
//            for (int i = 0; i < flagMd5.length(); i++) {
//                char a = flagMd5.charAt(i);
//                if (a == '0') {
//                    count++;
//                    sumLocation += i;
//                }
//            }
//            if (10 * count + sumLocation == 403) {
//                xxx
//            }
//         else {
//            this.finish();
//            }
//        */
//        int count = 0, sumLocation = 0;
//        int lenData = env->GetArrayLength(data);
//        auto* d = reinterpret_cast<char *>(env->GetByteArrayElements(data, 0));
//        for(int i=0; i<lenData; i++){
//            if(d[i] == '0'){
//                count ++;
//                sumLocation += i;
//            }
//        }
//        if(10 * count + sumLocation == 403){
//
//        }
//    }
//
//    JNIEXPORT jstring JNICALL Java_com_isccohhh_MainActivity_genData(JNIEnv* env, jobject thiz, jbyteArray data){
//        /*
//         input2.setEnabled(true);
//                char[] head = flagMd5.substring(0, 4).toCharArray();
//                long seed = (int) head[0] + (int) head[1] + (int) head[2] + (int) head[3] * 1000;
//                Random random = new Random(seed);
//                char[] ans = new char[32];
//                for (int i = 0; i < 32; i++) {
//                    ans[i] = HEX_DIGITS[random.nextInt(16)];
//                }
//                String flag = input2.getText().toString().trim();
//
//                for (int i = 0; i < flag.length(); i++) {
//                    if (flag.charAt(i) != ans[i]) {
//                        this.finish();
//                        break;
//                    }
//                }
//                Log.i(TAG, "OHHHHH, You get it!");
//         */
//
//    }
//
//}
