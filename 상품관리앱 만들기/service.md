# 가게 사장의 입장에서 상품을 등록하고 수정할 수 있는 기능이 기본으로 제공되며
# 고객의 입장에서 등록된 상품의 목록을 볼 수 있으며, 고객이 원하는 상품 선택시 상세화면이 제시되어 선택정보를 자세히 보여주는 어플 제작

상품 도메인 모델
- 상품 ID (각 상품을 구분하기 위한 Key)
- 상품명
- 가격
- 수량
상품 관리 기능
- 상품목록
- 상품상세
- 상품등록
- 상품수정

```java
//상품 객체 Item   
//코드 작성시 배우고 느낀점: 기본생성자 만드는것 까먹지 말자!
@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
    }
}

//상품 저장소 객체 (상품을 저장과 검색시 사용될 객체이며 저장 및 검색과 삭제 기능까지 추가함
//코드 작성시 배우고 느낀점: 상품 수정시 Id (상품 구별 key)를 이용해 값들을 저장하여 바꾸는것을 기억!
//기본키는 상품 새로 구성시 자동으로 하나씩 증가되게 만들어 값마다 코드구현하는 중복 제거하기

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public Item findById(Long id) {
        return store.get(id);
    }
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void clearStore() {
        store.clear();
    }
    
   # 상품관리앱 기본 뷰를 다른분들이 미리 제작해놓은 HTML과 CSS 다운로드 받아 사용 

//고객의 요청에 따른 해당 기능이 수행되게 하는 컨트롤러 클래스 
// //코드 작성시 배우고 느낀점: '중복되는것을 어떻게 줄일 수 있을까'를 항상 생각하기
                              Model객체는 스프링이 자동으로 만들어주니 바로 () 값에만 넣으면 사용가능
                              내가보여주고 싶어하는 화면을 return "뷰저장위치"로 ! 
                              해당 view에서 동적기능수행되게 구현하는것 잊지말기! <타임리프>
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor   //final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다. 따라서 final 키워드를 빼면 안된다!
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
     Item item  = itemRepository.findById(itemId);
    model.addAttribute("item",item);
    return "basic/item";
    }


    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
  @PostMapping("/add")
  public String save(@ModelAttribute Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId() ;
  }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
   
  
    
    
}
