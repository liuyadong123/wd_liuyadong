package com.example.lib_core.mvp;

import java.lang.ref.WeakReference;

public abstract class UPresenter<M,V> {
    public  M model;
    public  V view;
    public WeakReference<V> weakReference;
     public  abstract  M getModel();

     public  void attach(M model,V view){
         this.model=model;
         weakReference=new WeakReference<>(view);
         this.view=weakReference.get();
     }

     public void deattach(){
          weakReference.clear();
         this.weakReference=null;
         this.view=null;
     }


}
