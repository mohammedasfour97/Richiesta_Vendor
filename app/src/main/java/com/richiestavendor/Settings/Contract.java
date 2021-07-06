package com.richiestavendor.Settings;

import com.richiestavendor.ModelClasses.SubSection;
import com.richiestavendor.RoomDB.StoreDao;
import com.richiestavendor.Store;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Settings{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<Store> StoresList);
                void onFailure(String error);
            }
            void getStoreData(onFinishedListener onFinishedListener);
        }

        interface Presenter{

            void requestStoreData();
        }

        interface View{

            void onFinished(Store result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}
