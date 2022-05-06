import {
  MypageMenuBtn,
  MypageMenuText,
  MypageMenuTextContainer,
} from "../screens/styled";
import { MaterialCommunityIcons } from "@expo/vector-icons";

interface PropsType {
  menu: string;
  navigation: any;
}

const MypageMenuBox: React.FC<PropsType> = ({
  menu,
  navigation: { navigate },
}) => {
  const onPress = () => {
    switch (menu) {
      case "회원정보":
        navigate("Profile");
        break;
      case "계좌 관리하기":
        navigate("ConnectAccount");
        break;
      case "로그아웃":
        console.log("log OUT");
        break;
      case "공동인증 발급/재발급":
        navigate("Oauth");
        break;
      case "생체인증 발급/재발급":
        navigate("AuthBio");
        break;
      case "고객 문의":
        navigate("Service");
        break;
      case "회원 탈퇴":
        console.log("Delete user");
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
