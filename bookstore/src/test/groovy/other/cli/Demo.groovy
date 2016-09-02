package other.cli

import org.apache.commons.cli.*

/**
 * Created by Liutengfei on 2016/7/12 0012.
 */
class Demo {
    /**
     * 程序入口
     */
    public static void main(String[] args) throws IOException, ParseException {
        Options options = new Options();
        options.addOption("cf", "create-facade", true, "create-Facade");
        options.addOption("cs", "create-service", true, "create-Service");
        options.addOption("cd", "create-domain", true, "create-domain");

        options.addOption("ga", "generate-all", true, "generate Facade/Service/domain");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("nof", options);

        //继续执行程序代码
        println "在下面输入您的命令，当输入exit时退出"
        Scanner scanner = new Scanner(System.in)
        while (scanner.hasNext()) {
            String line = scanner.nextLine()
            if ("exit".equals(line?.trim())) {
                break
            }
            String[] params=line.split("\\s+");
            CommandLine cmd = parser.parse(options, params);
            String createFacade = cmd.getOptionValue('cf');
            String createService = cmd.getOptionValue('cs');
            String createDomain = cmd.getOptionValue('cd');

            println createFacade
            println createService
            println createDomain
            println "在下面输入您的命令，当输入exit时退出"
        }
        scanner.close();
    }
}
