package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class JokeFetchAsyncTest {

    @Test
    public void checkNonEmptyJoke() throws InterruptedException {
        assertTrue(true);

        // Creating  a countDownLatch to let the test know when asyncTask is done
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        EndpointsAsyncTask testTask = new EndpointsAsyncTask(InstrumentationRegistry.getContext(), null) {
            @Override
            protected void onPostExecute(String result) {
                // Checks for null
                assertNotNull(result);
                // Checks for non-empty string
                //assertTrue(result.trim().length() > 0);
                assertFalse(TextUtils.isEmpty(result));
                countDownLatch.countDown();
            }
        };
        testTask.execute();
        countDownLatch.await();
    }
}