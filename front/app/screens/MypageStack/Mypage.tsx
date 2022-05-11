import MypageMenuBox from "../../components/MypageMenuBox";
import { LayoutContainer, HeaderText, MypageMenuContainer } from "../styled";

interface PropsType {
  navigation: any;
  setLogin: (a: boolean) => void;
}

const Mypage: React.FC<PropsType> = ({ navigation, setLogin }) => {
  const menus = [
    "회원정보",
    "계좌 관리하기",
    "공동인증 발급/재발급",
    "생체인증 발급/재발급",
    "고객 문의",
    "회원 탈퇴",
    "로그아웃",
  ];

  return (
    <LayoutContainer>
      <HeaderText>내 정보</HeaderText>
      <MypageMenuContainer>
        {menus.map((menu, idx) => (
          <MypageMenuBox
            key={idx}
            menu={menu}
            navigation={navigation}
            setLogin={setLogin}
          />
        ))}
      </MypageMenuContainer>
    </LayoutContainer>
  );
};

export default Mypage;
