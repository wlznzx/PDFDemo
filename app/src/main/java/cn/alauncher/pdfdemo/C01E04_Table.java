package cn.alauncher.pdfdemo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class C01E04_Table {

    public static final String FOX = "/mnt/sdcard/timg.jpg";

    /**
     * 表格各种属性综合使用
     *
     * @throws IOException
     * @throws DocumentException
     */

    public static void main(String[] args) throws DocumentException, Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createTablePdf();
    }

    public static final String DEST = "/mnt/sdcard/table_android1.pdf";

    public static void createTablePdf() throws IOException, DocumentException {

        // PdfWriter writer = new PdfWriter(DEST);
        //Initialize PDF document
        // PdfDocument pdf = new PdfDocument(writer);


        Document document = new Document();
        // 创建PdfWriter对象
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        // 打开文档
        document.open();

        // 添加表格，4列
        PdfPTable table = new PdfPTable(4);
        //// 设置表格宽度比例为%100
        table.setWidthPercentage(100);
        // 设置表格的宽度
        table.setTotalWidth(500);
        // 也可以每列分别设置宽度
        table.setTotalWidth(new float[]{160, 70, 130, 100});
        // 锁住宽度
        table.setLockedWidth(true);
        // 设置表格上面空白宽度
        table.setSpacingBefore(10f);
        // 设置表格下面空白宽度
        table.setSpacingAfter(10f);
        // 设置表格默认为无边框
        table.getDefaultCell().setBorder(0);
        PdfContentByte cb = writer.getDirectContent();

        // 构建每个单元格
        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
        // 边框颜色
        cell1.setBorderColor(BaseColor.BLUE);
        // 设置背景颜色
        cell1.setBackgroundColor(BaseColor.ORANGE);
        // 设置跨两行
        cell1.setRowspan(2);
        // 设置距左边的距离
        cell1.setPaddingLeft(10);
        // 设置高度
        cell1.setFixedHeight(20);
        // 设置内容水平居中显示
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
        cell2.setBorderColor(BaseColor.GREEN);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
        cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        // 设置无边框
        cell3.setBorder(Rectangle.NO_BORDER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell3);

        // 在表格添加图片
        Image cellimg = Image.getInstance(FOX);
        PdfPCell cell4 = new PdfPCell(cellimg, true);
        cell4.setBorderColor(BaseColor.RED);
        cell4.setPaddingLeft(10);
        cell4.setFixedHeight(30);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell4);

        // 增加一个条形码到表格
        Barcode128 code128 = new Barcode128();
        code128.setCode("14785236987541");
        code128.setCodeType(Barcode128.CODE128);
        // 生成条形码图片
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        // 加入到表格
        PdfPCell cellcode = new PdfPCell(code128Image, true);
        cellcode.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellcode.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellcode.setFixedHeight(30);
        table.addCell(cellcode);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Cell 5"));
        cell5.setPaddingLeft(10);
        // 设置占用列数
        cell5.setColspan(2);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell5);
        document.add(table);
        // 关闭文档
        document.close();
    }

    // E4F2F6
    private static BaseColor titleColor = new BaseColor(228, 242, 246);

    private static BaseColor dataTitleColor = new BaseColor(92, 167, 188);

    private static BaseColor dataLineOneColor = new BaseColor(176, 217, 239);

    private static BaseColor dataLineTwoColor = new BaseColor(225, 240, 249);

    private static BaseColor dataOKColor = new BaseColor(0, 153, 102);
    //  0, 153, 102 合格

    private static ArrayList<String> signatureTitle = new ArrayList<String>();

    static {
        signatureTitle.add("课长");
        signatureTitle.add("系长");
        signatureTitle.add("担当");
    }

    private static ArrayList<String> titles = new ArrayList<String>();

    static {
        titles.add("部品名称");
        titles.add("进货批量");
        titles.add("进货日期");
        titles.add("检查日期");
    }

    private static ArrayList<Parameter2Bean> mParameter2Beans = new ArrayList<>();

    private static ArrayList<ResultBean3> mResultBean3s = new ArrayList<>();

    private static TemplateBean mTemplateBean = new TemplateBean();

    public static void initDatas() {
        for (int j = 1; j <= 7; j++) {
            Parameter2Bean _bean = new Parameter2Bean();
            _bean.setCode_id(1);
            _bean.setIndex(j);
            _bean.setEnable(true);
            _bean.setDescribe("内径" + j);
            _bean.setNominal_value(24.0);
            _bean.setUpper_tolerance_value(0.04);
            _bean.setLower_tolerance_value(0.0);
            mParameter2Beans.add(_bean);
        }

        for (int i = 0; i < 10; i++) {
            ResultBean3 _bean = new ResultBean3();
            _bean.setCodeID(1);
            _bean.setHandlerAccout("hhb");
            _bean.setResult("OK");
            _bean.setWorkid_extra("");
            ArrayList<String> values = new ArrayList<>();
            ArrayList<String> picPaths = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                values.add(String.valueOf(j));
                picPaths.add(String.valueOf(j));
            }
            _bean.setMValues(values);
            _bean.setMPicPaths(picPaths);
            mResultBean3s.add(_bean);
        }

        ArrayList<String> AQLList = new ArrayList<>();
        AQLList.add("无毛刺、无异物、无气孔");
        AQLList.add("无收缩、无裂缝、无缺陷");
        AQLList.add("毛刺高度控制在0.3以下");
        AQLList.add("外包装无破损、无变形、无潮湿");
        AQLList.add("月份标签确认");
        AQLList.add("供应商数据确认");
        ArrayList<String> RoHSList = new ArrayList<>();
        RoHSList.add("RoHS确认频率");
        RoHSList.add("本批确认");
        RoHSList.add("上回RoHS确认日");
        RoHSList.add("确认结果(RoHS检查数据以检查日期追溯)");
        RoHSList.add("模号：");
        mTemplateBean.setAQLList(AQLList);
        mTemplateBean.setRoHSList(RoHSList);
    }

    public static void createNTTable() throws IOException, DocumentException {

        initDatas();

        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bf, 8, Font.NORMAL);
        font.setColor(BaseColor.BLACK);

        Document document = new Document();
        // 创建PdfWriter对象
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));

        // writer.setPageEvent(new PDFBuilder());
        // 打开文档
        document.open();
        // 添加表格，4列
        PdfPTable table = new PdfPTable(16);
        // 构建每个单元格
        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
        // 边框颜色
        cell1.setBorderColor(BaseColor.BLACK);
        // 设置背景颜色
        cell1.setBackgroundColor(titleColor);
        // 设置跨两行
        cell1.setRowspan(2);
        // 列数;
        cell1.setColspan(10);
        // 设置距左边的距离
        cell1.setPaddingLeft(10);
        // 设置高度
        cell1.setFixedHeight(20);
        // 设置内容水平居中显示
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);


        for (int i = 0; i < signatureTitle.size(); i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(signatureTitle.get(i), font));
            cell.setRowspan(1);
            cell.setColspan(2);
            // 设置高度
            cell.setFixedHeight(20);
            // 设置内容水平居中显示
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 设置垂直居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            // table.addCell(cell);
        }
        /*         */
        for (int i = 0; i < signatureTitle.size(); i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(" "));
            cell.setRowspan(1);
            cell.setColspan(2);
            // 设置距左边的距离
            cell.setPaddingLeft(10);
            // 设置高度
            // 设置内容水平居中显示
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 设置垂直居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }

        for (int i = 0; i < titles.size(); i++) {
            table.addCell(getTitleCell(titles.get(i)));
            table.addCell(getTitleCell(" "));
        }

        for (int i = 0; i < titles.size(); i++) {
            table.addCell(getTitleCell(titles.get(i)));
            table.addCell(getTitleCell(" "));
        }

        Image cellIMG = Image.getInstance(FOX);
        PdfPCell cell4 = new PdfPCell(cellIMG, true);
        cell4.setRowspan(10);
        cell4.setColspan(16);
        cell4.setBorderColor(BaseColor.BLACK);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell4.setPaddingLeft(5);
        cell4.setPaddingRight(5);
        cell4.setPaddingTop(5);
        cell4.setPaddingBottom(5);
        table.addCell(cell4);

        /*
        for (int i = 0; i < titles.size(); i++) {
            table.addCell(getDataCell(titles.get(i), 2, 2));
            table.addCell(getDataCell(" ", 2, 2));
        }
         */

        //每页显示的记录数
        int pageSize = 3;
        //页数
        int pageSum = (int) Math.ceil((double) mParameter2Beans.size() / (double) pageSize);

        android.util.Log.d("wlDebug", "pageSum_ = " + pageSum);
        for (int i = 0; i < pageSum; i++) {
            // i * 3 + 0 > mParameter2Beans.size() -1  ? String.valueOf(mParameter2Beans.get(i * 3 + 0).getIndex()) : " ";

            Parameter2Bean rol1Bean = i * 3 + 0 <= mParameter2Beans.size() - 1 ? mParameter2Beans.get(i * 3 + 0) : null;
            Parameter2Bean rol2Bean = i * 3 + 1 <= mParameter2Beans.size() - 1 ? mParameter2Beans.get(i * 3 + 1) : null;
            Parameter2Bean rol3Bean = i * 3 + 2 <= mParameter2Beans.size() - 1 ? mParameter2Beans.get(i * 3 + 2) : null;
            // 绘制数据列第一行
            table.addCell(getDataCell("记号", 1, 1, dataTitleColor));
            table.addCell(getDataCell(rol1Bean != null ? String.valueOf(rol1Bean.getIndex()) : " ", 1, 5, dataTitleColor));
            table.addCell(getDataCell(rol2Bean != null ? String.valueOf(rol2Bean.getIndex()) : " ", 1, 5, dataTitleColor));
            table.addCell(getDataCell(rol3Bean != null ? String.valueOf(rol3Bean.getIndex()) : " ", 1, 5, dataTitleColor));

            // 绘制上限值;
            table.addCell(getDataCell("上限值", 1, 1, dataTitleColor));
            table.addCell(getDataCell(rol1Bean != null ?
                    String.valueOf(rol1Bean.getNominal_value() + rol1Bean.getUpper_tolerance_value()) : " ", 1, 5, dataLineOneColor));
            table.addCell(getDataCell(rol2Bean != null ?
                    String.valueOf(rol2Bean.getNominal_value() + rol2Bean.getUpper_tolerance_value()) : " ", 1, 5, dataLineOneColor));
            table.addCell(getDataCell(rol3Bean != null ?
                    String.valueOf(rol3Bean.getNominal_value() + rol3Bean.getUpper_tolerance_value()) : " ", 1, 5, dataLineOneColor));

            // 绘制下限值;
            table.addCell(getDataCell("下限值", 1, 1, dataTitleColor));
            table.addCell(getDataCell(rol1Bean != null ?
                    String.valueOf(rol1Bean.getNominal_value() + rol1Bean.getLower_tolerance_value()) : " ", 1, 5, dataLineTwoColor));
            table.addCell(getDataCell(rol2Bean != null ?
                    String.valueOf(rol2Bean.getNominal_value() + rol2Bean.getLower_tolerance_value()) : " ", 1, 5, dataLineTwoColor));
            table.addCell(getDataCell(rol3Bean != null ?
                    String.valueOf(rol3Bean.getNominal_value() + rol3Bean.getLower_tolerance_value()) : " ", 1, 5, dataLineTwoColor));

            // 绘制中间值;
            table.addCell(getDataCell("中间值", 1, 1, dataTitleColor));
            table.addCell(getDataCell(rol1Bean != null ?
                    String.valueOf(rol1Bean.getNominal_value()) : " ", 1, 5, dataLineOneColor));
            table.addCell(getDataCell(rol2Bean != null ?
                    String.valueOf(rol2Bean.getNominal_value()) : " ", 1, 5, dataLineOneColor));
            table.addCell(getDataCell(rol3Bean != null ?
                    String.valueOf(rol3Bean.getNominal_value()) : " ", 1, 5, dataLineOneColor));

            // 数据；
            for (int j = 0; j < mResultBean3s.size(); j++) {
                String value1 = i * 3 + 0 <= (mResultBean3s.get(j).getMValues().size() - 1) ? mResultBean3s.get(j).getMValues().get(i * 3 + 0) : " ";
                String path1 = i * 3 + 0 <= (mResultBean3s.get(j).getMPicPaths().size() - 1) ? mResultBean3s.get(j).getMPicPaths().get(i * 3 + 0) : " ";
                String value2 = i * 3 + 1 <= (mResultBean3s.get(j).getMValues().size() - 1) ? mResultBean3s.get(j).getMValues().get(i * 3 + 1) : " ";
                String path2 = i * 3 + 1 <= (mResultBean3s.get(j).getMPicPaths().size() - 1) ? mResultBean3s.get(j).getMPicPaths().get(i * 3 + 1) : " ";
                String value3 = i * 3 + 2 <= (mResultBean3s.get(j).getMValues().size() - 1) ? mResultBean3s.get(j).getMValues().get(i * 3 + 2) : " ";
                String path3 = i * 3 + 2 <= (mResultBean3s.get(j).getMPicPaths().size() - 1) ? mResultBean3s.get(j).getMPicPaths().get(i * 3 + 2) : " ";
                table.addCell(getDataCell(String.valueOf((j + 1)), 1, 1, dataTitleColor));
                table.addCell(getDataCell(value1, 1, 2, j % 2 == 1 ? dataLineOneColor : dataLineTwoColor));
                table.addCell(getDataCell(path1, 1, 3, j % 2 == 1 ? dataLineOneColor : dataLineTwoColor));
                table.addCell(getDataCell(value2, 1, 2, j % 2 == 1 ? dataLineOneColor : dataLineTwoColor));
                table.addCell(getDataCell(path2, 1, 3, j % 2 == 1 ? dataLineOneColor : dataLineTwoColor));
                table.addCell(getDataCell(value3, 1, 2, j % 2 == 1 ? dataLineOneColor : dataLineTwoColor));
                table.addCell(getDataCell(path3, 1, 3, j % 2 == 1 ? dataLineOneColor : dataLineTwoColor));
            }

            // 最大值；

            // 最小值；

            // 判定;
        }
        // 底部 6 ，6 ，4；
        int bottomRow = Math.max(Math.max(mTemplateBean.getAQLList().size(), mTemplateBean.getRoHSList().size()), 5);

        android.util.Log.d("wlDebug", "bottomRow = " + bottomRow);

        table.addCell(getDataCell("外观检查一般I级\nAQL=0.15", bottomRow, 2, dataTitleColor));
        table.addCell(getDataCell(mTemplateBean.getAQLList().get(0), 1, 5, dataTitleColor));
        table.addCell(getDataCell("RoHS相关: ", bottomRow, 2, dataTitleColor));
        table.addCell(getDataCell(mTemplateBean.getRoHSList().get(0), 1, 5, dataTitleColor));
        table.addCell(getDataCell("合格", 3, 2, dataTitleColor));

        for (int i = 1; i < 4; i++) {
            table.addCell(getDataCell("记号", 1, 5, dataTitleColor));
            table.addCell(getDataCell("记号", 1, 5, dataTitleColor));
        }

        table.addCell(getDataCell("不合格", 2, 2, dataTitleColor));

        for (int i = 4; i < bottomRow; i++) {
            table.addCell(getDataCell("记号", 1, 5, dataTitleColor));
            table.addCell(getDataCell("记号", 1, 5, dataTitleColor));
        }
        document.add(table);
        // 关闭文档
        document.close();
    }


    private static PdfPCell getTitleCell(String msg) {
        PdfPCell cell = new PdfPCell(new Paragraph(msg, getFont(8, BaseColor.BLACK)));
        cell.setRowspan(1);
        cell.setColspan(2);
        // 设置距左边的距离
        // 设置高度
        cell.setFixedHeight(20);
        // 设置内容水平居中显示
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private static PdfPCell getDataCell(String msg, int row, int col, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Paragraph(msg, getFont(6, BaseColor.WHITE)));
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBackgroundColor(backgroundColor);
        cell.setRowspan(row);
        cell.setColspan(col);
        // 设置距左边的距离
        // 设置高度
        cell.setFixedHeight(10);
        // 设置内容水平居中显示
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private static PdfPCell getDataCellNum(String msg, int row, int col) {
        PdfPCell cell = new PdfPCell(new Paragraph(msg));
        cell.setRowspan(row);
        cell.setColspan(col);
        // 设置距左边的距离
        // 设置高度
        cell.setFixedHeight(10);
        // 设置内容水平居中显示
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private static Font font = null;

    private static Font getFont(int size, BaseColor color) {
        try {
            if (font == null) {
                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                font = new Font(bf, 8, Font.NORMAL);
                font.setColor(BaseColor.BLACK);
            }
            font.setSize(size);
            font.setColor(color);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }

    // 模板
    public PdfTemplate total;

    // 基础字体对象
    public BaseFont bf = null;

    // 利用基础字体生成的字体对象，一般用于生成中文文字
    public Font fontDetail = null;

    public int presentFontSize = 8;

    public String header = "itext测试页眉";

    public void addPage(PdfWriter writer, Document document) {
        //设置分页页眉页脚字体
        try {
            if (bf == null) {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            }
            if (fontDetail == null) {
                fontDetail = new Font(bf, presentFontSize, Font.NORMAL);// 数据体字体
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.写入页眉
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, new Phrase(header, fontDetail),
                document.left(), document.top() + 20, 0);
        // 2.写入前半部分的 第 X页/共
        int pageS = writer.getPageNumber();
        String foot1 = "第 " + pageS + " 页 /共";
        Phrase footer = new Phrase(foot1, fontDetail);

        // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
        float len = bf.getWidthPoint(foot1, presentFontSize);

        // 4.拿到当前的PdfContentByte
        PdfContentByte cb = writer.getDirectContent();

        // 5.写入页脚1，x轴就是(右margin+左margin + right() -left()- len)/2.0F
        // 再给偏移20F适合人类视觉感受，否则肉眼看上去就太偏左了
        // ,y轴就是底边界-20,否则就贴边重叠到数据体里了就不是页脚了；注意Y轴是从下往上累加的，最上方的Top值是大于Bottom好几百开外的。
        ColumnText.showTextAligned(
                cb,
                Element.ALIGN_CENTER,
                footer,
                (document.rightMargin() + document.right()
                        + document.leftMargin() - document.left() - len) / 2.0F + 20F,
                document.bottom() - 20, 0);

        // 6.写入页脚2的模板（就是页脚的Y页这俩字）添加到文档中，计算模板的和Y轴,X=(右边界-左边界 - 前半部分的len值)/2.0F +
        // len ， y 轴和之前的保持一致，底边界-20
        cb.addTemplate(total, (document.rightMargin() + document.right()
                        + document.leftMargin() - document.left()) / 2.0F + 20F,
                document.bottom() - 20); // 调节模版显示的位置

    }
}
