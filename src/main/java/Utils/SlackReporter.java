package Utils;

import TestObjects.World;
import com.ullink.slack.simpleslackapi.SlackAttachment;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.ChannelHistoryModule;
import com.ullink.slack.simpleslackapi.impl.ChannelHistoryModuleFactory;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SlackReporter {
    private static String projectName = "Team App AutoTest- ";
   

    private static String detailedReportUrlAndroid = "";
    private static String detailedReportUrlIOS = " ";		
    //public static String slackToken = "";
    public static String slackToken = ""; 
   // public static String channelName = "team-f";
    public static String channelName = "test1";
    private static String passedThumbUrl = "";
    private static String failedThumbUrl = "";

    private static SlackSession session;
    private static SlackChannel channel;

    private static void connect()
    {
        session = SlackSessionFactory.createWebSocketSlackSession(slackToken);
        try {
            session.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        channel = session.findChannelByName(channelName);
    }

    public static void sendMessage() {
        connect();
        SlackAttachment att = getReport();
        if (World.isAndroid)
            projectName = String.format("%sAndroid", projectName);
        else
            projectName = String.format("%siOS", projectName);

        session.sendMessage(channel, projectName, att);
    }

    public static void deleteAllMessages(){
        connect();

        ChannelHistoryModule channelHistoryModule = ChannelHistoryModuleFactory.createChannelHistoryModule(session);

        List<SlackMessagePosted> messages = channelHistoryModule.fetchHistoryOfChannel(channel.getId());
        for (SlackMessagePosted message: messages) {
            session.deleteMessage(message.getTimeStamp(), channel);
        }
    }

    private static SlackAttachment getReport() {
        long count = ResultsStorage.resultStorage.size();
        long passedCount = ResultsStorage.resultStorage.stream().filter((r) -> r.result.equalsIgnoreCase("passed")).count();
        long failedCount = ResultsStorage.resultStorage.stream().filter((r) -> r.result.equalsIgnoreCase("failed")).count();
        long ignoredCount = ResultsStorage.resultStorage.stream().filter((r) -> !r.result.equalsIgnoreCase("passed") && !r.result.equalsIgnoreCase("failed")).count();

        String formattedText = String.format("Total:\t%s\nPassed:\t%s\nFailed:\t%s\nIgnored:\t%s\n", count, passedCount, failedCount, ignoredCount);

        // Print failed test names.
        if (failedCount != 0) {
            List<ScenarioResult> failedTestNames = ResultsStorage.resultStorage.stream().filter((r) -> r.result.equalsIgnoreCase("failed")).collect(Collectors.toList());

            String names = "";
            for (ScenarioResult result : failedTestNames)
                names = String.format("%s%s - %s\n", names, result.feature.toUpperCase(), result.scenario);
            formattedText = String.format("%sFailed tests:\n%s", formattedText, names);
        }

        SlackAttachment att = new SlackAttachment("Results", "fallback", formattedText, "");

        if (failedCount != 0) {
            // red.
            att.setColor("#FF3030");
            att.setThumbUrl(failedThumbUrl);
        } else {
            // green.
            att.setColor("#00B000");
            att.setThumbUrl(passedThumbUrl);
        }
        if (World.isAndroid)
        		att.setFooter(String.format("Details: %s", detailedReportUrlAndroid));
        		else
        		att.setFooter(String.format("Details: %s", detailedReportUrlIOS));
        
        return att;
    }
}
