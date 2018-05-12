/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.legacy_gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * A class for manually triggering various debugging actions
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@Controller
public class DebugController {

//    @Autowired
//    private TMMessageSubmitter sub;
//
//    // static String[] usernames = { "carolynzebedee", "tracyhobson",
//    // "jacobharrison", "jamesberry", "kevin.tann",
//    // "lindsayc", "maria", "ThomasPoot" };
//    // String[] usernames = {"carolynzebedee", "tracyhobson","maria"};
//    static String[] usernames = { "tricia.parsons" };
//    // static String[] usernames = { "jamesberry" };
//    // private String[][] addressLines = { { "33 Elm Park", "Stanmore" }, { "6A
//    // Ollershaw Ln", "Marston", "Northwich" },
//    // { "2 Bryn Deri", "Llanychaer", "Fishguard" }, { "10 Minter Cl", "Densole",
//    // "Folkestone" },
//    // { "4 Sutton Ln", "Clerkenwell", "London" }, { "253 Norwich Rd", "Wymondham"
//    // },
//    // { "65 High St", "Stoke-on-Trent" }, { "81 Gilmore Rd", "London" },
//    // { "33 Harle Rd", "Backworth", "Newcastle upon Tyne" }, { "41 Sunnybank",
//    // "Epsom" },
//    // { "1 Charles Hastings Way", "Worcester" }, { "1 Forestdale", "Hindhead" },
//    // { "4 Wadingburn Ln", "Lasswade" }, { "8 Ailey St", "Earby", "Barnoldswick" },
//    // {"13 Carter St", "Uttoxeter"} };
//    // private String[] postcodes = {"HA7 4AU", "CW9 6ES" , "SA65 9TE", "CT18 7DX" ,
//    // "EC1V 0DN" , "NR18 0SL" , "ST6 5TA" , "SE13 5AB" , "NE27 0RY" , "KT18 7DY" ,
//    // "WR5 1WS" , "GU26 6TA" , "EH18 1HG" , "BB18 6RL" ,"ST14 8HE"};
//
//    // private String[][] addressLines = {{"19 Brook St","Neston"},{"6 Morrison
//    // Cres", "Kinlochleven"},{"5 Walton Rd", "Pattinson North", "Washington"},{"1
//    // Channel View", "Port Talbot"},{"14 Purlings Rd", "Bushey"}};
//    // private String[] postcodes = {"CH64 9XJ", "PH50 4QY", "NE38 8QE", "SA12 6JF",
//    // "WD23 3BL"};
//    private String[][] addressLines = { { "37 THE COPPICE", "EASINGTON COLLIERY", "PETERLEE" },
//            { "4 ARGENT STREET", "PETERLEE" },{ "7 GARDNER COURT", "PETERLEE" },
//            { "6 BOSTON STREET", "PETERLEE" },{ "20 GAYFIELD TERRACE", "GRANTS HOUSES HORDEN", "PETERLEE" },};
//    private String[] postcodes = { "SR8 3NU", "SR8 3QA", "SR8 3RR", "SR8 3SL", "SR8 3TA" };
//    private String[] quotas = { "51808-11-803", "51808-12-803","51808-13-803","51808-14-803","51808-15-803" };
//    private String[] surveys = { "SLC" };
//    // private String[][] addressLines = { { "16A Heathfield Ave", "Fareham" }, {
//    // "72 Red Barn Ln", "Fareham" },
//    // { "53 Inverness Ave", "Fareham" }, { "44 Iron Mill Cl", "Fareham" },
//    // { "20 Oldenburg", "Whiteley", "Fareham" }, { "59 Woodbourne Cl", "Fareham" },
//    // { "43 Park Cottage Dr", "Fareham" }, { "2A Gudge Heath Ln", "Fareham" },2222';';r3
//    // { "45 Whiteley Way", "Whiteley", "Fareham" }, { "19 Margarita Rd", "Fareham"
//    // } };
//    // private String[] postcodes = { "PO15 5QA", "PO15 6HD", "PO15 6AS", "PO15
//    // 6LB", "PO15 7EJ", "PO15 5QJ", "PO15 5JD",
//    // "PO15 5AA", "PO15 7BS", "PO15 5HE" };
//
//    // private Float[][] coordinates = { { new Float(50.862674), new
//    // Float(-1.2475704) } };
//    // private String[][] addressLines = { { "Office for National Statistics",
//    // "Segensworth Road", "Titchfield" } };
//    // private String[] postcodes = { "PO15 5RR" };
//
//    // private Float[][] coordinates = { { new Float(50.862674), new
//    // Float(-1.2475704) },{ new Float(50.862674), new Float(-1.2475704) },{ new
//    // Float(50.862674), new Float(-1.2475704) },{ new Float(50.862674), new
//    // Float(-1.2475704) },{ new Float(50.862674), new Float(-1.2475704) },{ new
//    // Float(50.862674), new Float(-1.2475704) },{ new Float(50.862674), new
//    // Float(-1.2475704) },{ new Float(50.862674), new Float(-1.2475704) },{ new
//    // Float(50.862674), new Float(-1.2475704) },{ new Float(1), new Float(1) },{
//    // new Float(1), new Float(1) },{ new Float(1), new Float(1) },{ new Float(1),
//    // new Float(1) },{ new Float(1), new Float(1) },{ new Float(1), new Float(1)
//    // },{ new Float(1), new Float(1) },{ new Float(1), new Float(1) },{ new
//    // Float(1), new Float(1) },{ new Float(0), new Float(0) },{ new Float(0), new
//    // Float(0) },{ new Float(0), new Float(0) },{ new Float(0), new Float(0) },{
//    // new Float(0), new Float(0) },{ new Float(0), new Float(0) },{ new Float(0),
//    // new Float(0) },{ new Float(0), new Float(0) },{ new Float(0), new Float(0) }
//    // };
//    // private String[][] addressLines = { { "Office for National Statistics",
//    // "Segensworth Road", "Titchfield" },{ "Office for National Statistics",
//    // "Segensworth Road", "Titchfield" },{ "Office for National Statistics",
//    // "Segensworth Road", "Titchfield" },{ "XXXXX", "XXXXX", "XXXXX" },{ "XXXXX",
//    // "XXXXX", "XXXXX" },{ "XXXXX", "XXXXX", "XXXXX" },{ "", "", "" },{ "", "", ""
//    // },{ "", "", "" },{ "Office for National Statistics", "Segensworth Road",
//    // "Titchfield" },{ "Office for National Statistics", "Segensworth Road",
//    // "Titchfield" },{ "Office for National Statistics", "Segensworth Road",
//    // "Titchfield" },{ "XXXXX", "XXXXX", "XXXXX" },{ "XXXXX", "XXXXX", "XXXXX" },{
//    // "XXXXX", "XXXXX", "XXXXX" },{ "", "", "" },{ "", "", "" },{ "", "", "" },{
//    // "Office for National Statistics", "Segensworth Road", "Titchfield" },{
//    // "Office for National Statistics", "Segensworth Road", "Titchfield" },{
//    // "Office for National Statistics", "Segensworth Road", "Titchfield" },{
//    // "XXXXX", "XXXXX", "XXXXX" },{ "XXXXX", "XXXXX", "XXXXX" },{ "XXXXX", "XXXXX",
//    // "XXXXX" },{ "", "", "" },{ "", "", "" },{ "", "", "" } };
//    // private String[] postcodes = { "PO15 5RR", "", "XXXXX","PO15 5RR", "",
//    // "XXXXX","PO15 5RR", "", "XXXXX","PO15 5RR", "", "XXXXX","PO15 5RR", "",
//    // "XXXXX","PO15 5RR", "", "XXXXX","PO15 5RR", "", "XXXXX","PO15 5RR", "",
//    // "XXXXX","PO15 5RR", "", "XXXXX" };
//
//    // private Float[][] coordinates = { { new Float(1), new Float(1) } };
//    // private String[][] addressLines = { { "XXXXX", "XXXXX", "XXXXX" } };
//    // private String[] postcodes = { "XXXXX" };
//
//    @Autowired
//    public DebugController() {
//    }
//
//    @GetMapping("/debug")
//    public String showDebugButtons(Model model, RedirectAttributes redirectAttributes) {
//        return "debug/buttons";
//    }
//
//    @PostMapping("/debug/unallocCleardown")
//    public String cleardownAllUnallocatedJobs(RedirectAttributes redirectAttributes) throws Exception {
//        // System.out.println("Foo!");
//        // long start = System.currentTimeMillis();
//        ArrayList<String> allJobIds = new ArrayList<String>();
//        JAXBElement<QueryMessagesRequest> queryMessagesRequest = DebugMessage.createQueryMessagesRequest("TypeName",
//                "Consilium.TASKMobile.Visits.Messages.ForceRecallVisitResponse, Consilium.TASKMobile.Visits.Messages");
//        JAXBElement<QueryMessagesResponse> queryMessagesResponse = sub.send(queryMessagesRequest);
//
//        for (MessageInfoType queryResponse : queryMessagesResponse.getValue().getMessages().getMessage()) {
//            JAXBElement<GetMessageRequest> getMessageRequest = DebugMessage
//                    .createGetMessageRequest(queryResponse.getId());
//            JAXBElement<GetMessageResponse> getMessageResponse = sub.send(getMessageRequest);
//
//            Unmarshaller u = JAXBContext.newInstance(ObjectFactory.class).createUnmarshaller();
//            String getMessageResponseContent = getMessageResponse.getValue().getContent();
//            getMessageResponseContent = getMessageResponseContent.replaceAll("&lt;", "<");
//            getMessageResponseContent = getMessageResponseContent.replaceAll("&gt;", ">");
//
//            JAXBElement<ForceRecallVisitResponse> forceRecallVisitResponse = (JAXBElement<ForceRecallVisitResponse>) u
//                    .unmarshal(new StringReader(getMessageResponseContent));
//            allJobIds.add(forceRecallVisitResponse.getValue().getIdentity().getReference());
//        }
//
//        for (String id : allJobIds) {
//            SendDeleteJobRequestMessage sendDeleteJobRequestMessage = DebugMessage
//                    .createSendDeleteJobRequestMessage(id);
//            sub.send(sendDeleteJobRequestMessage);
//            // System.out.print(id + " ");
//        }
//        // long end = System.currentTimeMillis();
//        // System.out.println(allJobIds.size() + " - " + (end - start));
//
//        // getDuplicates(allJobIds);
//        // System.out.println();
//        // System.out.println("NUMBER OF UNALLOC JOBS FOUND --> " + allJobIds.size());
//
//        return "redirect:/debug/";
//    }
//
//    public static <T extends Comparable<T>> void getDuplicates(ArrayList<T> array) {
//        Set<T> dupes = new HashSet<T>();
//        for (T i : array) {
//            if (!dupes.add(i)) {
//                System.out.println("Duplicate element in array is : " + i);
//            }
//        }
//    }
//
//    @PostMapping("/debug/cleardown")
//    public String cleardownAllJobs(RedirectAttributes redirectAttributes) throws Exception {
//        // System.out.println("Foo!");
//        // long start = System.currentTimeMillis();
//        ArrayList<String> allJobIds = new ArrayList<String>();
//        JAXBElement<QueryMessagesRequest> queryMessagesRequest = DebugMessage.createQueryMessagesRequest("TypeName",
//                "Consilium.TASKMobile.Visits.Messages.CreateVisitRequest, Consilium.TASKMobile.Visits.Messages");
//        JAXBElement<QueryMessagesResponse> queryMessagesResponse = sub.send(queryMessagesRequest);
//
//        for (MessageInfoType queryResponse : queryMessagesResponse.getValue().getMessages().getMessage()) {
//            JAXBElement<GetMessageRequest> getMessageRequest = DebugMessage
//                    .createGetMessageRequest(queryResponse.getId());
//            JAXBElement<GetMessageResponse> getMessageResponse = sub.send(getMessageRequest);
//
//            Unmarshaller u = JAXBContext.newInstance(ObjectFactory.class).createUnmarshaller();
//            String getMessageResponseContent = getMessageResponse.getValue().getContent();
//            getMessageResponseContent = getMessageResponseContent.replaceAll("&lt;", "<");
//            getMessageResponseContent = getMessageResponseContent.replaceAll("&gt;", ">");
//            ObjectFactory objectFactory = new ObjectFactory();
//            JAXBElement<CreateVisitRequest> createVisitRequest = (JAXBElement<CreateVisitRequest>) u
//                    .unmarshal(new StringReader(getMessageResponseContent));
//            String allocatedUser = createVisitRequest.getValue().getVisit().getIdentity().getUser().getName();
//            if (allocatedUser.equals("tracyhobson") || allocatedUser.equals("carolynzebedee")) {
//                allJobIds.add(createVisitRequest.getValue().getVisit().getIdentity().getReference());
//            }
//        }
//
//        for (String id : allJobIds) {
//             SendDeleteJobRequestMessage sendDeleteJobRequestMessage = DebugMessage.createSendDeleteJobRequestMessage(id);
//             sub.send(sendDeleteJobRequestMessage);
//        }
//        // long end = System.currentTimeMillis();
//        // System.out.println(allJobIds.size() + " - " + (end-start));
//        // System.out.println();
//        System.out.println("total jobs " + allJobIds.size());
//
//        return "redirect:/debug/";
//    }
//
//    @PostMapping("/debug/singleAlloc")
//    public String sendSingleAllocation(RedirectAttributes redirectAttributes) {
//        // System.out.println("Foo!");
//        return "redirect:/debug/";
//    }
//
//    @PostMapping("/debug/multiAlloc")
//    public String sendMultipleAllocations(RedirectAttributes redirectAttributes) throws Exception {
//        // long start = System.currentTimeMillis();
//        for (String username : usernames) {
//            for (int i = 0; i < addressLines.length; i++) {
//                sub.send(DebugMessage.createSendCreateJobRequestMessage(username, addressLines[i], postcodes[i],
//                        quotas[i], surveys[0]));
//            }
//        }
//        // long end = System.currentTimeMillis();
//        // System.out.println((end-start));
//
//        return "redirect:/debug/";
//    }
//
//    @PostMapping("/debug/queryMessagesResponse")
//    public String sendGetQuery(RedirectAttributes redirectAttributes) {
//
//        return "redirect:/debug/";
//    }
}
