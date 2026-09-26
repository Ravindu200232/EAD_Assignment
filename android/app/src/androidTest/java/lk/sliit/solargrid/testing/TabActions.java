package lk.sliit.solargrid.testing;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Matcher;

public final class TabActions {

    /** Nobody builds this class; it is only a helper. */
    private TabActions() {
    }

    /** Chooses the tab at the given position, as a tap on it would. */
    public static ViewAction selectTab(int position) {
        return new ViewAction() {

            /** Only a TabLayout has tabs to choose. */
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TabLayout.class);
            }

            /** What Espresso prints if this step fails. */
            @Override
            public String getDescription() {
                return "choose tab " + position;
            }

            /** Chooses the tab and lets the app react to it. */
            @Override
            public void perform(UiController uiController, View view) {
                TabLayout.Tab tab = ((TabLayout) view).getTabAt(position);
                if (tab == null) {
                    throw new AssertionError("There is no tab at position " + position + ".");
                }
                tab.select();
                uiController.loopMainThreadUntilIdle();
            }
        };
    }
}
