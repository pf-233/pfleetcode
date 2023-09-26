package Interview;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test {

//    Given a string s = "This is a {good} day, do you want to go {hiking} or do you want to {go {running}}". The given string is just a sample, you can imagine any valid input strings to replace the substrings in the brackets.
//
//    Given another common function to get a string array for a given string: vector<string> GetValues(string givenString). All returned values from GetValues will not contain brackets any more, but the original given string may have nested brackets.
//    For the given example string above, GetValues may return as below. Pls note that, below are just examples, GetValues() may return any valid string array.
//
//    GetValues("good") -> ["A", "B"]
//    GetValues("hiking")->["C", "D"]
//    GetValues("running")->["E", "F"]
//    GetValues("go E") -> ["G"]
//    GetValues("go F")->["H"]
//
//    Question: write a common solution to replace all substrings wrapped by brackets with all the possible values returned from the given function for a given string and return the result.
//
//    For the given example string and function above, the returned result should be an array like this:
//            [
//            "This is a A day, do you want to go C or do you want to G",
//            "This is a A day, do you want to go C or do you want to H",
//            "This is a A day, do you want to go D or do you want to G",
//            "This is a A day, do you want to go D or do you want to H",
//            "This is a B day, do you want to go C or do you want to G",
//            "This is a B day, do you want to go C or do you want to H",
//            "This is a B day, do you want to go D or do you want to G",
//            "This is a B day, do you want to go D or do you want to H"
//            ]

//    public static List<String> main(String[] args) {
//        String str = "This is a {good} day, do you want to go {hiking} or do you want to {go {running}}";
//        return replaceString(str, 0);
//    }

//    private static List<String> replaceString(String s, int start) {
//        int leftCnt = 0;
//        int end = start;
//        if (end == s.length()) {
//            return new LinkedList<>();
//        }
//        Stack<Integer> stack = new Stack<>();
//        Stack<List<String>> preStack = new Stack<>();
//        List<String> tempPreList = null;
//        List<String> subList = new LinkedList<>();
//        for (; end < s.length(); end++) {
//            char ch = s.charAt(end);
//            if (ch == '{') {
//                leftCnt++;
//                stack.push(end);
//                String preStr = end > start ? s.substring(start, end) : "";
//                tempPreList.add(preStr);
//                preStack.push(tempPreList);
//            } else if (ch == '}') {
//                leftCnt--;
//                int top = stack.pop();
//                List<String> tempList = replaceString(s.substring(top + 1, end), 0);
//                tempPreList = preStack.pop();
//                subList = new LinkedList<>();
//                int nextNorInd = end;
//                while (nextNorInd < s.length() && s.charAt(nextNorInd) != '{' && s.charAt(nextNorInd) != '}') {
//                    nextNorInd++;
//                }
//                String after = s.substring(end + 1, nextNorInd);
//                for (String temp : tempList) {
//                    for (String preTemp : tempPreList) {
//                        subList.add(preTemp + temp + after);
//                    }
//                }
//                end = nextNorInd - 1;
//            }
//        }
//        List<String> values = null;
//        if (cnt == 0) {
//            values = GetValues(s.substring(start + 1, end));
//
//        } else {
//            int tempInd = getFirstCharacter(s, start + 1);
//            String subStr = s.substring(tempInd, end);
//            int nextInd = getFirstCharacter(subStr, 0);
//            List<String> nextList = new LinkedList();
//            nextList.add(subStr.substring(0, nextInd));
//            List<String> subvalues = replaceString(subStr, nextList, nextInd);
//            values = new LinkedList();
//            for (String val : subvalues) {
//                for (String temp : GetValues(s.substring(start + 1, end))) {
//                    values.add(temp + val);
//                }
//            }
//        }
//
//        List<String> ans = new LinkedList();
//        for (String value : values) {
//            for (String temp : list) {
//                ans.add(temp + value);
//            }
//        }
//        if (end < s.length()) {
//            return replaceString(s, ans, getFirstCharacter(s, end + 1));
//        } else {
//            return ans;
//        }
//    }

    private static List<String> GetValues(String str) {
        return new LinkedList<>();
    }
    private static int getFirstCharacter(String s, int start) {
        while (start < s.length() && s.charAt(start) != '{') {
            start++;
        }
        return start;
    }
}
