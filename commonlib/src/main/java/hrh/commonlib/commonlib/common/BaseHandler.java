package hrh.commonlib.commonlib.common;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class BaseHandler<T> extends Handler {
    WeakReference<T> wr;     //弱引用
    BaseHandler.BaseHandlerCallBack callBack;

    public BaseHandler(T t,BaseHandler.BaseHandlerCallBack callBack){
        wr = new WeakReference<>(t);
        this.callBack = callBack;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (wr == null){
            return;
        }
        T t = wr.get();//如果此方法为空, 那么说明wr指向的对象已经被回收了.
        if(t != null && callBack != null){
            callBack.callBack(msg);
        }
    }

    public interface BaseHandlerCallBack {
        /**
         * 提供的BaseHandler的callback返回接口
         * @param msg  传递的Message信息
         */
        void callBack(Message msg);
    }

    public void destroy(){
        wr = null;
        callBack =  null;
        removeCallbacksAndMessages(null);
    }
}