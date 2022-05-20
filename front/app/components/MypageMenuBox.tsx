import {
  MypageMenuBtn,
  MypageMenuText,
  MypageMenuTextContainer,
} from "../screens/styled";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { Alert } from "react-native";

interface PropsType {
  // 메뉴명
  menu: string;
  // 로그인 여부 set
  setLogin: (a: any) => void;
  // stack navigation
  navigation: any;
}

// Component _ MypageMenuBox
const MypageMenuBox: React.FC<PropsType> = ({
  menu,
  navigation: { navigate },
  setLogin,
}) => {
  // method
  const onPress = () => {
    switch (menu) {
      // 회원정보
      case "회원정보":
        navigate("Profile");
        break;
      // 계좌 관리하기
      case "계좌 관리하기":
        navigate("ConnectAccount");
        break;
      // OTP 정보 등록
      case "OTP 정보 등록":
        // navigate("Oauth");
        break;
      // 생체인증 정보 등록
      case "생체인증 정보 등록":
        navigate("AuthBio");
        break;
      // 고객 문의
      case "고객 문의":
        navigate("Service");
        break;
      // 회원 탈퇴
      case "회원 탈퇴":
        navigate("Delete");
        break;
      // 로그아웃
      case "로그아웃":
        AsyncStorage.removeItem("accessToken");
        AsyncStorage.removeItem("refreshToken");
        setLogin(false);
        break;
      default:
        Alert.alert("wrong menu");
        break;
    }
  };
  return (
    <MypageMenuBtn onPress={onPress}>
      <MypageMenuTextContainer>
        <MypageMenuText able={menu === "OTP 정보 등록" ? false : true}>
          {menu}
        </MypageMenuText>
        <MaterialCommunityIcons
          name="chevron-right"
          size={35}
          color={menu === "OTP 정보 등록" ? "grey" : "black"}
        />
      </MypageMenuTextContainer>
    </MypageMenuBtn>
  );
};
export default MypageMenuBox;
