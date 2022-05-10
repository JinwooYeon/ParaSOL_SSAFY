import {
  MypageMenuBtn,
  MypageMenuText,
  MypageMenuTextContainer,
} from "../screens/styled";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import AsyncStorage from "@react-native-async-storage/async-storage";

interface PropsType {
  menu: string;
  navigation: any;
  setLogin: (a: any) => void;
}

const MypageMenuBox: React.FC<PropsType> = ({
  menu,
  navigation: { navigate },
  setLogin,
}) => {
  const onPress = () => {
    switch (menu) {
      case "회원정보":
        navigate("Profile");
        console.log("Profile");
        break;
      case "계좌 관리하기":
        navigate("ConnectAccount");
        console.log("ConnectAccount");
        break;
      case "공동인증 발급/재발급":
        navigate("Oauth");
        console.log("Oauth");
        break;
      case "생체인증 발급/재발급":
        navigate("AuthBio");
        console.log("AuthBio");
        break;
      case "고객 문의":
        navigate("Service");
        console.log("Service");
        break;
      case "회원 탈퇴":
        navigate("Delete");
        console.log("Delete user");
        break;
      case "로그아웃":
        AsyncStorage.clear();
        setLogin(false);
        console.log("log OUT");
        break;
      default:
        console.log("wrong menu");
        break;
    }
  };
  return (
    <MypageMenuBtn onPress={onPress}>
      <MypageMenuTextContainer>
        <MypageMenuText>{menu}</MypageMenuText>
        <MaterialCommunityIcons name="chevron-right" size={35} color="black" />
      </MypageMenuTextContainer>
    </MypageMenuBtn>
  );
};
export default MypageMenuBox;
