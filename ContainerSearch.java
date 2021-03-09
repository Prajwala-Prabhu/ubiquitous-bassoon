package scripts;

import driver.BaseClass;
import pages.ContainerSearchPage;

public class ContainerSearch extends BaseClass {
        public static void main(String args[]){
                initiateTest("Explore Containers");

//                Step 1
                openBrowser("https://hub.docker.com/search");

//                a
                ContainerSearchPage.get().verifyContainerTab();

//                b
                ContainerSearchPage.get().verifyExistenceVerifiedPublisherCheckbox();
                ContainerSearchPage.get().verifyExistenceOfficialImagesCheckbox();

//                c
                ContainerSearchPage.get().verifyExistenceAnalyticsCheckbox();
                ContainerSearchPage.get().verifyExistenceBaseImagesCheckbox();
                ContainerSearchPage.get().verifyExistenceDatabaseCheckbox();
                ContainerSearchPage.get().verifyExistencStorageCheckbox();

//                 Step 2
                ContainerSearchPage.get().applyVerifiesPublisherFilter();

//                Step 3
                ContainerSearchPage.get().applyDatabaseFilter();
                ContainerSearchPage.get().applyBaseImageFilter();

//                Step 4
                ContainerSearchPage.get().removeDatabaseFilter();

                getWebdriver().quit();
                endTest();
        }
}
