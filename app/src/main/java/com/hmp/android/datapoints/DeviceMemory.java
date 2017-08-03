package com.hmp.android.datapoints;

import android.os.Environment;
import android.os.StatFs;

/**
 * Created by harsh.arora on 7/17/2017.
 */

public class DeviceMemory {

    public static float getInternalStorageSpace() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        //StatFs statFs = new StatFs("/data");
        float total = ((float)statFs.getBlockCount() * statFs.getBlockSize()) / 1048576;
        return total;
    }

    public static float getInternalFreeSpace() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        //StatFs statFs = new StatFs("/data");
        float free  = ((float)statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1048576;
        return free;
    }

    public static float getInternalUsedSpace() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        //StatFs statFs = new StatFs("/data");
        float total = ((float)statFs.getBlockCount() * statFs.getBlockSize()) / 1048576;
        float free  = ((float)statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1048576;
        float busy  = total - free;
        return busy;
    }
}